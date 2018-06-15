package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PolarClockSettings;
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

    private PolarClockSettings settings;
    final Paint arcPaint;
    final Paint textPaint;
    final Path textPath;
    final RectF rect;
    float currentSweepAngle;
    float newSweepAngle;
    ArcText arcText;
    public ArcType arcType;

    Arc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        this.radius = radius;
        float arcOffsetFactor = (float) 1 / 8;
        ArcOffsetConstant = radius * arcOffsetFactor;
        currentSweepAngle = 0;
        arcPaint = new Paint();
        textPaint = new Paint();
        textPath = new Path();
        rect = new RectF(0, 0, 0, 0);
        this.settings = settings;
        arcText = new ArcText(arcColor);
        initializeArcPaint(arcColor);
    }

    private void initializeArcPaint(int arcColor) {
        arcPaint.setStyle(ArcStyle);
        arcPaint.setStrokeWidth(CircleStrokeWidth);
        arcPaint.setAntiAlias(SetCircleAntiAlias);
        arcPaint.setStrokeCap(StrokeCap);
        arcPaint.setColor(arcColor);
    }

    public @ColorInt int getArcTextColor() {
        return arcText.textColor;
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
        private boolean visible;
        private char[] text;
        private Paint paint;
        private Path path;
        private int pathLength;

        public @ColorInt int textColor;

        ArcText(@ColorInt int arcColor) {
            paint = new Paint();
            path = new Path();

            visible = settings.showArcText;
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setStyle(Paint.Style.FILL);
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textColor = getTextColor(arcColor);
            paint.setColor(textColor);
            paint.setTextSize(30);
        }

        private @ColorInt int getTextColor(@ColorInt int arcColor) {
            @ColorInt int color;
            switch (settings.getArcTextColorSetting()) {
                case BLACK:
                    color = Color.BLACK;
                    break;
                case DYNAMIC:
                    color = ColorAnalyzer.isColorBright(arcColor) ? Color.BLACK : Color.WHITE;
                    break;
                case WHITE:
                default:
                    color = Color.WHITE;
                    break;
            }
            return color;
        }

        void setVisible(boolean visible) {
            this.visible = visible;
        }

        void updateText(String text) {
            if (visible) {
                this.text = text.toCharArray();
            }
        }

        void updateLength() {
            if (visible) {
                path.rewind();
                path.arcTo(rect, ArcStartingAngle, currentSweepAngle);
                PathMeasure pathMeasure = new PathMeasure(path, false);
                float length = pathMeasure.getLength();
                pathLength =  paint.breakText(text, 0, text.length, length, null);
            }
        }

        void draw(Canvas canvas) {
            if (visible) {
                canvas.drawTextOnPath(text, 0, pathLength, path, 0, 12, paint);
            }
        }
    }
}
