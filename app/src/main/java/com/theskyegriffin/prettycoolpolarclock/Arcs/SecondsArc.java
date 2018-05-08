package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class SecondsArc extends Arc {
    private int currentSecond;

    public SecondsArc(int radius, @ColorInt int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 5;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
        arcType = ArcTypes.Seconds;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentSecond = currentDateTime.getTime().getSeconds();
        float minutePercentComplete = (float) currentSecond / 60;
        newSweepAngle = minutePercentComplete * MaxArcSweepAngle;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            text = (currentSecond + " SECONDS").toCharArray();
            textLength = getTextPathLength(text);
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }

}
