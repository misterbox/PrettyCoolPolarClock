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
        int updatedHour = currentDateTime.getTime().getHours();

        if (updatedHour != currentHour) {
            currentHour = updatedHour;
            float dayPercentComplete = (float) currentHour / 24;
            newSweepAngle = dayPercentComplete * MaxArcSweepAngle;
            arcText = (currentHour + " HOURS").toCharArray();
        }
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            textLength = getTextPathLength(arcText);
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(arcText, 0, textLength, textPath, 0, 12, textPaint);
    }

}
