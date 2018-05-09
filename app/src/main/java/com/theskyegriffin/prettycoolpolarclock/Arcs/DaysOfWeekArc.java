package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;
import java.util.Locale;

public class DaysOfWeekArc extends Arc {
    private String currentDayDisplayName;
    private int currentDay;

    public DaysOfWeekArc(int radius, @ColorInt int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 2;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
        arcType = ArcTypes.DaysOfWeek;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedDay = currentDateTime.getTime().getDay();

        if (updatedDay != currentDay) {
            currentDay = updatedDay;
            currentDayDisplayName = currentDateTime.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
            float weekPercentComplete = (float) (currentDay + 1) / 7;
            newSweepAngle = weekPercentComplete * MaxArcSweepAngle;
            arcText = currentDayDisplayName == null ? new char[]{} : currentDayDisplayName.toCharArray();
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
