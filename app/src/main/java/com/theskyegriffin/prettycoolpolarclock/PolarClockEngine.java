package com.theskyegriffin.prettycoolpolarclock;

import android.view.SurfaceHolder;

import java.util.Calendar;

public class PolarClockEngine {
    private SurfaceHolder surfaceHolder;
    private Calendar currentTime;

    public PolarClockEngine() {

    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void updateCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
        onUpdateCurrentTime();
    }

    private void onUpdateCurrentTime() {

    }
}
