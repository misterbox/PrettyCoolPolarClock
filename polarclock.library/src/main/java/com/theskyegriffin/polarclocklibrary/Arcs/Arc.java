package com.theskyegriffin.polarclocklibrary.Arcs;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;

import com.theskyegriffin.polarclocklibrary.PolarClockView;
import com.theskyegriffin.polarclocklibrary.Utilities.ColorAnalyzer;

import java.util.Calendar;

public abstract class Arc {
    float RectangleOffset = 0;
    static float ArcOffsetConstant;
    float ArcOffsetMultiple = 0;
    final float ArcStartingAngle = 270;
    private static final int CircleStrokeWidth = 50;
    private final boolean SetCircleAntiAlias = true;
    private final Paint.Style ArcStyle = Paint.Style.STROKE;
    private final Paint.Cap StrokeCap = Paint.Cap.ROUND;

    final Paint arcPaint;
    final Paint textPaint;
    final Path textPath;
    final RectF rect;
    private final int radius;
    private final PolarClockView clockView;
    float currentSweepAngle;
    float newSweepAngle;

    public float getRectangleOffset() {
        return RectangleOffset;
    }

    Arc(PolarClockView clockView, int radius, @ColorInt int arcColor){
        this.radius = radius;
        float arcOffsetFactor = (float) 1 / 8;
        ArcOffsetConstant = radius * arcOffsetFactor;
        this.clockView = clockView;
        currentSweepAngle = 0;
        arcPaint = new Paint();
        textPaint = new Paint();
        textPath = new Path();
        rect = new RectF(0, 0, 0, 0);

        InitializeArcPaint(arcColor);
        InitializeTextPaint(arcColor);
    }

    private void InitializeArcPaint(int arcColor) {
        arcPaint.setStyle(ArcStyle);
        arcPaint.setStrokeWidth(CircleStrokeWidth);
        arcPaint.setAntiAlias(SetCircleAntiAlias);
        arcPaint.setStrokeCap(StrokeCap);
        arcPaint.setColor(arcColor);
    }

    private void InitializeTextPaint(@ColorInt int arcColor) {
        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        @ColorInt int textColor = GetTextColor(arcColor);
        textPaint.setColor(textColor);
        textPaint.setTextSize(30);
    }

    private int GetTextColor(@ColorInt int arcColor) {
        return ColorAnalyzer.isColorBright(arcColor) ? Color.BLACK : Color.WHITE;
    }

    public void startAnimation() {
        if (currentSweepAngle != newSweepAngle) {
            ValueAnimator animation = ValueAnimator.ofFloat(currentSweepAngle, newSweepAngle);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentSweepAngle = (float) animation.getAnimatedValue();
                    clockView.postInvalidate();
                }
            });
            animation.start();
        }
    }

    int getTextPathLength(char[] text) {
        PathMeasure pathMeasure = new PathMeasure(textPath, false);
        float textPathLength = pathMeasure.getLength();
        return textPaint.breakText(text, 0, text.length, textPathLength, null);
    }

    void resetTextPath() {
        textPath.reset();
        textPath.arcTo(rect, ArcStartingAngle, currentSweepAngle);
    }

    public abstract void updateCurrentTime(Calendar currentDateTime);

    public abstract void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint);

    void setCanvasRectangle(int viewHeightMidpoint, int viewWidthMidpoint) {
        int startLeftPoint = viewWidthMidpoint - radius;
        int startTopPoint = viewHeightMidpoint - radius;
        int startRightPoint = viewWidthMidpoint + radius;
        int startBottomPoint = viewHeightMidpoint + radius;
        rect.set(startLeftPoint + RectangleOffset, startTopPoint + RectangleOffset, startRightPoint - RectangleOffset, startBottomPoint - RectangleOffset);
    }
}
