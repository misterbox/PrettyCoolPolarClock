package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class HoursArc extends Arc {
    private int currentHour;

    public HoursArc(int radius, @ColorInt int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 3;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
        arcType = ArcTypes.Hours;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentHour = currentDateTime.getTime().getHours();
        float dayPercentComplete = (float) currentHour / 24;
        newSweepAngle = dayPercentComplete * MaxArcSweepAngle;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            text = (currentHour + " HOURS").toCharArray();
            textLength = getTextPathLength(text);
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }

}
