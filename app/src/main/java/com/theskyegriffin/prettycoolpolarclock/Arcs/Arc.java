package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public abstract class Arc {
    private final float radius;
    @ColorInt private final int arcColor;
    Calendar currentTime;

    final float ArcOffsetConstant;
    float ArcOffsetMultiple = 1;
    float RectangleOffset = 0;

    ArcType arcType;
    final Paint arcPaint;
    final RectF rect;

    final float MaxArcSweepAngle = 359.9f;
    float startAngle = 270;
    float sweepAngle = 0;
    float updatedSweepAngle = 0;

    Arc(float radius, @ColorInt int color) {
        this.radius = radius;
        this.arcColor = color;
        float arcOffsetFactor = (float) 1 / 8;
        ArcOffsetConstant = radius * arcOffsetFactor;

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

    public abstract void setCurrentTime(Calendar currentTime);

    public float getCurrentSweepAngle() {
        return sweepAngle;
    }

    public float getUpdatedSweepAngle() {
        return updatedSweepAngle;
    }

    public ArcDrawable getDrawable(float viewHeightMidpoint, float viewWidthMidpoint) {
        float startLeftPoint = viewWidthMidpoint - radius;
        float startTopPoint = viewHeightMidpoint - radius;
        float startRightPoint = viewWidthMidpoint + radius;
        float startBottomPoint = viewHeightMidpoint + radius;
        rect.set(startLeftPoint + RectangleOffset, startTopPoint + RectangleOffset, startRightPoint - RectangleOffset, startBottomPoint - RectangleOffset);

        return new ArcDrawable(rect, startAngle, sweepAngle, arcPaint);
    }
}
