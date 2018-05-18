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
    float ArcOffsetFactor = 0;
    final float ArcStartingAngle = 270;
    final float MaxArcSweepAngle = 359.9f;
    static float ArcOffsetConstant;

    final Paint arcPaint;
    final Paint textPaint;
    final Path textPath;
    final RectF rect;
    float currentSweepAngle;
    float newSweepAngle;

    public ArcTypes arcType;
    ArcText arcText;

    Arc(int radius, @ColorInt int arcColor, boolean showArcText) {
        this.radius = radius;
        float arcOffsetFactor = (float) 1 / 8;
        ArcOffsetConstant = radius * arcOffsetFactor;
        currentSweepAngle = 0;
        arcPaint = new Paint();
        textPaint = new Paint();
        textPath = new Path();
        rect = new RectF(0, 0, 0, 0);
        arcText = new ArcText(arcColor, showArcText);

        InitializeArcPaint(arcColor);
    }

    private void InitializeArcPaint(int arcColor) {
        arcPaint.setStyle(ArcStyle);
        arcPaint.setStrokeWidth(CircleStrokeWidth);
        arcPaint.setAntiAlias(SetCircleAntiAlias);
        arcPaint.setStrokeCap(StrokeCap);
        arcPaint.setColor(arcColor);
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

    void CalculateArcParameters(int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
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

    class ArcText {
        private final boolean visible;
        private char[] text;
        private Paint paint;
        private Path path;
        private int pathLength;

        ArcText(@ColorInt int arcColor, boolean visible) {
            this.visible = visible;
            paint = new Paint();
            path = new Path();

            if (this.visible) {
                paint.setTextAlign(Paint.Align.RIGHT);
                paint.setStyle(Paint.Style.FILL);
                paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                @ColorInt int textColor = GetTextColor(arcColor);
                paint.setColor(textColor);
                paint.setTextSize(30);
            }
        }

        private int GetTextColor(@ColorInt int arcColor) {
            return ColorAnalyzer.isColorBright(arcColor) ? Color.BLACK : Color.WHITE;
        }

        void UpdateText(String text) {
            if (visible) {
                this.text = text.toCharArray();
            }
        }

        void UpdateLength() {
            if (visible) {
                path.rewind();
                path.arcTo(rect, ArcStartingAngle, currentSweepAngle);
                PathMeasure pathMeasure = new PathMeasure(path, false);
                float length = pathMeasure.getLength();
                pathLength =  paint.breakText(text, 0, text.length, length, null);
            }
        }

        void Draw(Canvas canvas) {
            if (visible) {
                canvas.drawTextOnPath(text, 0, pathLength, path, 0, 12, paint);
            }
        }
    }
}
