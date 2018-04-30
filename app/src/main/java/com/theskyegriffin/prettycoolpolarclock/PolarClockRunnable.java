package com.theskyegriffin.prettycoolpolarclock;

import android.os.Handler;

import java.util.Calendar;

public class PolarClockRunnable implements Runnable {
    private final Handler handler;
    private final PolarClock polarClock;

    public PolarClockRunnable(Handler handler, PolarClock clock) {
        this.handler = handler;
        polarClock = clock;
    }

    @Override
    public void run() {
        Calendar currentDateTime = Calendar.getInstance();
        polarClock.updateCurrentTime(currentDateTime);
//        polarClock.startAnimation();
        start();
    }

    public void start() {
        handler.postDelayed(this, 1000);
    }
}
