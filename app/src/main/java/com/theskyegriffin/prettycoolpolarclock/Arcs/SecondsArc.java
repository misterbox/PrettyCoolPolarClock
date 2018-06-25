package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.support.annotation.ColorInt;

public class SecondsArc extends Arc {
    public SecondsArc(float radius, @ColorInt int color) {
        super(radius, color);
        arcType = ArcType.SECONDS;
        ArcOffsetMultiple = 5;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }
}
