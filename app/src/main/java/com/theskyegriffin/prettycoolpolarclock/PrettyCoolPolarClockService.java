package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
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

public class PrettyCoolPolarClockService extends WallpaperService implements SharedPreferences.OnSharedPreferenceChangeListener {
    boolean showArcText;

    @Override
    public Engine onCreateEngine() {
        ReadSettings();
        return new PolarClockWallpaperEngine();
    }

    private void ReadSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(PrettyCoolPolarClockService.this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        showArcText = sharedPreferences.getBoolean(PolarClockSettingsActivity.KEY_PREF_SHOW_ARC_TEXT, true);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("WALLPAPER SERVICE", "preference changed");
    }

    @Override
    public void onDestroy() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(PrettyCoolPolarClockService.this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    class PolarClockWallpaperEngine extends Engine {
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
        private ArcAnimationSet animationSet;

        private final Runnable polarClockRunner = new Runnable() {
            @Override
            public void run() {
                updateCurrentTime();
                startAnimations();
                handler.postDelayed(polarClockRunner, 1000);
            }
        };

        PolarClockWallpaperEngine() {
            handler = new Handler();
            InitializeDependencies();
        }

        private void InitializeDependencies() {
            arcs = new ArrayList<Arc>();
            arcs.add(new SecondsArc(radius, secondsArcColor, showArcText));
            arcs.add(new MinutesArc(radius, minutesArcColor, showArcText));
            arcs.add(new HoursArc(radius, hoursArcColor, showArcText));
            arcs.add(new DaysOfWeekArc(radius, daysOfWeekArcColor, showArcText));
            arcs.add(new DaysArc(radius, daysArcColor, showArcText));
            arcs.add(new MonthsArc(radius, monthsArcColor, showArcText));
        }

        @Override
        public void onVisibilityChanged(boolean isVisible) {
            Log.d("ENGINE", "onVisibilityChanged: " + isVisible);
            this.isVisible = isVisible;

            if (this.isVisible) {
                onSettingsChanged();
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
            animationSet.stop();
            handler.removeCallbacks(polarClockRunner);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
            Log.d("ENGINE", "onSurfaceChanged, width: " + width + " height: " + height);
            super.onSurfaceChanged(surfaceHolder, format, width, height);
            this.width = width;
            this.height = height;
            onSettingsChanged();
        }

        public void onSettingsChanged() {
            Log.d("ENGINE", "showArcText: " + showArcText);
            for (Arc arc : arcs) {
                arc.setArcSettings(showArcText);
            }
        }

        private void updateCurrentTime() {
            Calendar currentDateTime = Calendar.getInstance();

            for (Arc arc : arcs) {
                arc.updateCurrentTime(currentDateTime);
            }
        }

        private void startAnimations() {
            animationSet = new ArcAnimationSet(this);

            for (Arc arc : arcs) {
                float currentSweepAngle = arc.getCurrentSweepAngle();
                float newSweepAngle = arc.getNewSweepAngle();

                if (currentSweepAngle != newSweepAngle) {
                    ValueAnimator animator = ValueAnimator.ofFloat(arc.getCurrentSweepAngle(), arc.getNewSweepAngle()).setDuration(500);
                    animationSet.add(animator, arc);
                }
            }

            animationSet.start();
        }

        public void draw() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();

                if (canvas != null) {
                    canvas.drawColor(Color.BLACK);

                    int heightMidpoint = height / 2;
                    int widthMidpoint = width / 2;

                    for (Arc arc : arcs) {
                        arc.draw(canvas, heightMidpoint, widthMidpoint);
                    }
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
        }
    }
}
