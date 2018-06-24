package com.theskyegriffin.prettycoolpolarclock.Arcs;

import java.util.Calendar;

public abstract class Arc {
    private Calendar currentTime;

    public ArcType arcType;

    public Calendar getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }
}
