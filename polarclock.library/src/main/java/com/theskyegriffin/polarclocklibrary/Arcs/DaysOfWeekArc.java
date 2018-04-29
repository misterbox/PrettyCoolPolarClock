package com.theskyegriffin.polarclocklibrary.Arcs;

import android.graphics.Canvas;

import com.theskyegriffin.polarclocklibrary.PolarClockView;

import java.util.Calendar;
import java.util.Locale;

public class DaysOfWeekArc extends Arc {
    private String currentDayDisplayName;

    public DaysOfWeekArc(PolarClockView clockView, int radius, int arcColor) {
        super(clockView, radius, arcColor);
        ArcOffsetMultiple = 2;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int currentDay = currentDateTime.getTime().getDay();
        currentDayDisplayName = currentDateTime.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
        float weekPercentComplete = (float) (currentDay + 1) / 7;
        newSweepAngle = weekPercentComplete * 360;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = currentDayDisplayName == null ? new char[]{} : currentDayDisplayName.toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
