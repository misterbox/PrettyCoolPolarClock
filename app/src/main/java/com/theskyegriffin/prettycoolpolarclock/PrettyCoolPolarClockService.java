package com.theskyegriffin.prettycoolpolarclock;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Calendar;

public class PrettyCoolPolarClockService extends WallpaperService {
    PolarClockSettings settings;

    @Override
    public Engine onCreateEngine() {
        readSettings();
        return new PolarClockWallpaperEngine();
    }

    private void readSettings() {
        Log.d("WALLPAPER SERVICE", "Reading settings");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(PrettyCoolPolarClockService.this);
        boolean showArcText = sharedPreferences.getBoolean(PolarClockSettingsActivity.KEY_PREF_SHOW_ARC_TEXT, true);
        String arcTextColor = sharedPreferences.getString(PolarClockSettingsActivity.KEY_PREF_ARC_TEXT_COLOR, "white");
        settings = new PolarClockSettings(showArcText, arcTextColor);
    }

    class PolarClockWallpaperEngine extends Engine {
        private final Handler handler;
        private SurfaceHolder surfaceHolder;
        private boolean isVisible = false;
        private PolarClockEngine clock;

        private final Runnable polarClockRunner = new Runnable() {
            @Override
            public void run() {
                updateTime();
                handler.postDelayed(polarClockRunner, 1000);
            }
        };

        PolarClockWallpaperEngine() {
            handler = new Handler();
            clock = new PolarClockEngine();
        }

        @Override
        public void onVisibilityChanged(boolean isVisible) {
            Log.d("ENGINE", "onVisibilityChanged: " + isVisible);
            this.isVisible = isVisible;

            if (this.isVisible) {
                handler.postDelayed(polarClockRunner, 1000);
            }
            else {
                handler.removeCallbacks(polarClockRunner);
            }
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            Log.d("ENGINE", "onSurfaceCreated");
            surfaceHolder = holder;
            clock.setSurfaceHolder(holder);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d("ENGINE", "onSurfaceDestroyed");
            super.onSurfaceDestroyed(surfaceHolder);
            this.isVisible = false;
            handler.removeCallbacks(polarClockRunner);
            clock.setSurfaceHolder(null);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
            Log.d("ENGINE", "onSurfaceChanged, width: " + width + " height: " + height);
            super.onSurfaceChanged(surfaceHolder, format, width, height);
            this.surfaceHolder = surfaceHolder;
            clock.setSurfaceHolder(surfaceHolder);
            clock.setViewDimensions(height, width);
        }

        private void updateTime() {
            Calendar currentDateTime = Calendar.getInstance();
            clock.updateCurrentTime(currentDateTime);
        }

        private void draw() {
            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();

                if (canvas != null) {
                    Log.d("ENGINE", "drawing");
                    canvas.drawColor(Color.BLACK);
                }
            }
            catch (Exception e) {
                Log.d("ENGINE", "Error getting canvas from surface holder on draw" + System.lineSeparator() +
                        "Message: " + e.getMessage() + System.lineSeparator() +
                        "Stack trace: " + e.getStackTrace());
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
