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

public class ArcText {
    private final boolean visible;
    private char[] text;
    private Paint paint;
    private Path path;
    private int pathLength;

    public ArcText(@ColorInt int arcColor, boolean visible) {
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

    public void UpdateText(String text) {
        if (visible) {
            this.text = text.toCharArray();
        }
    }

    public void UpdateLength(RectF rect, float startAngle, float sweepAngle) {
        if (visible) {
            path.rewind();
            path.arcTo(rect, startAngle, sweepAngle);
            PathMeasure pathMeasure = new PathMeasure(path, false);
            float length = pathMeasure.getLength();
            pathLength =  paint.breakText(text, 0, text.length, length, null);
        }
    }

    public void Draw(Canvas canvas) {
        if (this.visible) {
            canvas.drawTextOnPath(text, 0, pathLength, path, 0, 12, paint);
        }
    }
}
