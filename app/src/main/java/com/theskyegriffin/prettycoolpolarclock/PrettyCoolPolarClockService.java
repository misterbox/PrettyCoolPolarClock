package com.theskyegriffin.prettycoolpolarclock;

import android.os.Handler;
import android.service.wallpaper.WallpaperService;

import com.theskyegriffin.polarclocklibrary.PolarClock;
import com.theskyegriffin.polarclocklibrary.PolarClockRunnable;

public class PrettyCoolPolarClockService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new PolarClockWallpaperEngine();
    }

    private class PolarClockWallpaperEngine extends Engine {
        private final Handler handler;
        private final PolarClock polarClock;
        private final PolarClockRunnable polarClockRunnable;

        PolarClockWallpaperEngine() {
            handler = new Handler();
            polarClock = new PolarClock();
            polarClockRunnable = new PolarClockRunnable(handler, polarClock);
        }
    }
}
