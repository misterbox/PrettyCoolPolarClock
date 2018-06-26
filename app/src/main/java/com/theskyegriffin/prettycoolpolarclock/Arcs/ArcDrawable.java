package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.RectF;

public class ArcDrawable {
    public RectF rect;
    public float startAngle;
    public float sweepAngle;

    public ArcDrawable(RectF rect, float startAngle, float sweepAngle) {
        this.rect = rect;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
    }
}
