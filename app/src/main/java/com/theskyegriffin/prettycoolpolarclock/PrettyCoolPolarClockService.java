package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysOfWeekArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.HoursArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MinutesArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MonthsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import java.util.ArrayList;
import java.util.Calendar;

public class PrettyCoolPolarClockService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new PolarClockWallpaperEngine();
    }

    public class PolarClockWallpaperEngine extends Engine {
        private final Handler handler;
        private ArrayList<Arc> arcs;
        private int width;
        private int height;
        private final int radius = 450;
        private final int monthsArcColor = Color.parseColor("#A95383");
        private final int daysArcColor = Color.parseColor("#D29867");
        private final int daysOfWeekArcColor = Color.parseColor("#52A852");
        private final int hoursArcColor = Color.parseColor("#3E7E7E");
        private final int minutesArcColor = Color.parseColor("#A3C460");
        private final int secondsArcColor = Color.parseColor("#D26767");
        private boolean isVisible = false;

        private final Runnable polarClockRunner = new Runnable() {
            @Override
            public void run() {
                updateCurrentTime();
                startAnimations();
            }
        };

        PolarClockWallpaperEngine() {
            handler = new Handler();
            InitializeDependencies();
        }

        private void InitializeDependencies() {
            arcs = new ArrayList<Arc>();
            arcs.add(new SecondsArc(this, radius, secondsArcColor));
            arcs.add(new MinutesArc(this, radius, minutesArcColor));
            arcs.add(new HoursArc(this, radius, hoursArcColor));
            arcs.add(new DaysOfWeekArc(this, radius, daysOfWeekArcColor));
            arcs.add(new DaysArc(this, radius, daysArcColor));
            arcs.add(new MonthsArc(this, radius, monthsArcColor));
        }

        @Override
        public void onVisibilityChanged(boolean isVisible) {
            Log.d("ENGINE", "onVisibilityChanged: " + isVisible);
            this.isVisible = isVisible;

            if (isVisible) {
                handler.postDelayed(polarClockRunner, 1000);
            }
            else {
                handler.removeCallbacks(polarClockRunner);
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d("ENGINE", "onSurfaceDestroyed");
            super.onSurfaceDestroyed(surfaceHolder);
            this.isVisible = false;
            handler.removeCallbacks(polarClockRunner);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
            Log.d("ENGINE", "onSurfaceChanged, width: " + width + " height: " + height);
            this.width = width;
            this.height = height;
            super.onSurfaceChanged(surfaceHolder, format, width, height);
        }

        private void updateCurrentTime() {
            Calendar currentDateTime = Calendar.getInstance();

            for (Arc arc : arcs) {
                arc.updateCurrentTime(currentDateTime);
            }
        }

        private void startAnimations() {
            ArcAnimationSet animationSet = new ArcAnimationSet(this);

            for (Arc arc : arcs) {
                ValueAnimator animator = ValueAnimator.ofFloat(arc.getCurrentSweepAngle(), arc.getNewSweepAngle());
                animationSet.add(animator, arc);
            }

            animationSet.start();
        }

        public void draw() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.BLACK);

                int heightMidpoint = height / 2;
                int widthMidpoint = width / 2;

                for (Arc arc : arcs) {
                    arc.draw(canvas, heightMidpoint, widthMidpoint);
                }
            }
            catch (Exception e) {
                Log.d("ENGINE", "Error getting canvas from surface holder on draw\n" +
                        "Message: " + e.getMessage() + "\n" +
                        "Stack trace: " + e.getStackTrace());
            }
            finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            handler.removeCallbacks(polarClockRunner);

            if (isVisible) {
                handler.postDelayed(polarClockRunner, 1000);
            }
        }
    }
}
