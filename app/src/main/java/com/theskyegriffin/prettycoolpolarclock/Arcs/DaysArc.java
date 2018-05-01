package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;

import java.util.Calendar;

public class DaysArc extends Arc {
    private int currentDate;

    public DaysArc(int radius, int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 1;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentDate = currentDateTime.getTime().getDate();
        int daysInMonth = currentDateTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        float monthPercentComplete = (float) currentDate / daysInMonth;
//        newSweepAngle = monthPercentComplete * 360;
        currentSweepAngle = monthPercentComplete * 360;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = (currentDate + " DAYS").toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
