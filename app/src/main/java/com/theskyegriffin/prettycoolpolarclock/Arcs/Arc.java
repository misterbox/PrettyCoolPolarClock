package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public abstract class Arc {
    private final float radius;
    @ColorInt private final int arcColor;
    private Calendar currentTime;

    ArcType arcType;
    final Paint arcPaint;
    final RectF rect;

    Arc(float radius, @ColorInt int color) {
        this.radius = radius;
        this.arcColor = color;

        arcPaint = new Paint();
        rect = new RectF(0, 0, 0, 0);
        configurePaint();
    }

    private void configurePaint() {
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(50);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setColor(arcColor);
    }

    public Calendar getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
    }

    public ArcDrawable getDrawable(float viewHeightMidpoint, float viewWidthMidpoint) {
        float startLeftPoint = viewWidthMidpoint - radius;
        float startTopPoint = viewHeightMidpoint - radius;
        float startRightPoint = viewWidthMidpoint + radius;
        float startBottomPoint = viewHeightMidpoint + radius;
        rect.set(startLeftPoint, startTopPoint, startRightPoint, startBottomPoint);

        return new ArcDrawable(rect);
    }
}
