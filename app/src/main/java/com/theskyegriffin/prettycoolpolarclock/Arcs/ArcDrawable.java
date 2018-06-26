package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Paint;
import android.graphics.RectF;

public class ArcDrawable {
    public RectF rect;
    public float startAngle;
    public float sweepAngle;
    public Paint paint;

    public ArcDrawable(RectF rect, float startAngle, float sweepAngle, Paint paint) {
        this.rect = rect;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.paint = paint;
    }
}
