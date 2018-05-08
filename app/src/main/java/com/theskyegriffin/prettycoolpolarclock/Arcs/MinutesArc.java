package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class MinutesArc extends Arc {
    private int currentMinute;

    public MinutesArc(int radius, @ColorInt int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 4;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
        arcType = ArcTypes.Minutes;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentMinute = currentDateTime.getTime().getMinutes();
        float hourPercentComplete = (float) currentMinute / 60;
        newSweepAngle = hourPercentComplete * MaxArcSweepAngle;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            text = (currentMinute + " MINUTES").toCharArray();
            textLength = getTextPathLength(text);
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
