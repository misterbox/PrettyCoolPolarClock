package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.Utilities.ColorAnalyzer;

import java.util.Calendar;

public abstract class Arc {
    private final int radius;
    private static final int CircleStrokeWidth = 50;
    private final boolean SetCircleAntiAlias = true;
    private final Paint.Style ArcStyle = Paint.Style.STROKE;
    private final Paint.Cap StrokeCap = Paint.Cap.ROUND;
    float RectangleOffset = 0;
    float ArcOffsetMultiple = 0;
    final float ArcStartingAngle = 270;
    final float MaxArcSweepAngle = 359.9f;
    static float ArcOffsetConstant;

    final Paint arcPaint;
    final Paint textPaint;
    final Path textPath;
    final RectF rect;
    float currentSweepAngle;
    float newSweepAngle;
    char[] arcText;
    int textLength;

    public ArcTypes arcType;

    Arc(int radius, @ColorInt int arcColor){
        this.radius = radius;
        float arcOffsetFactor = (float) 1 / 8;
        ArcOffsetConstant = radius * arcOffsetFactor;
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

    public float getRectangleOffset() {
        return RectangleOffset;
    }

    public float getCurrentSweepAngle() {
        return currentSweepAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle) {
        this.currentSweepAngle = currentSweepAngle;
    }

    public float getNewSweepAngle() {
        return newSweepAngle;
    }

    public void setNewSweepAngle(float newSweepAngle) {
        this.newSweepAngle = newSweepAngle;
    }

    private int GetTextColor(@ColorInt int arcColor) {
        return ColorAnalyzer.isColorBright(arcColor) ? Color.BLACK : Color.WHITE;
    }

    void CalculateArcParameters(int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
    }

    private void resetTextPath() {
        textPath.rewind();
        textPath.arcTo(rect, ArcStartingAngle, currentSweepAngle);
    }

    int getTextPathLength(char[] text) {
        PathMeasure pathMeasure = new PathMeasure(textPath, false);
        float textPathLength = pathMeasure.getLength();
        return textPaint.breakText(text, 0, text.length, textPathLength, null);
    }

    private void setCanvasRectangle(int viewHeightMidpoint, int viewWidthMidpoint) {
        int startLeftPoint = viewWidthMidpoint - radius;
        int startTopPoint = viewHeightMidpoint - radius;
        int startRightPoint = viewWidthMidpoint + radius;
        int startBottomPoint = viewHeightMidpoint + radius;
        rect.set(startLeftPoint + RectangleOffset, startTopPoint + RectangleOffset, startRightPoint - RectangleOffset, startBottomPoint - RectangleOffset);
    }

    public abstract void updateCurrentTime(Calendar currentDateTime);

    public abstract void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint);
}
