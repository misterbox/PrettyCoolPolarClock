package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.support.annotation.ColorInt;

import java.util.Calendar;

public class SecondsArc extends Arc {
    private int currentSecond;

    public SecondsArc(float radius, @ColorInt int color) {
        super(radius, color);
        arcType = ArcType.SECONDS;
        ArcOffsetMultiple = 5;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
        currentSecond = currentTime.get(Calendar.SECOND);
        float minutePercentComplete = (float) currentSecond / 60;
        sweepAngle = minutePercentComplete * MaxArcSweepAngle;
    }
}
