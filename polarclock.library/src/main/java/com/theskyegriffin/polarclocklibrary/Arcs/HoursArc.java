package com.theskyegriffin.polarclocklibrary.Arcs;

import android.graphics.Canvas;

import com.theskyegriffin.polarclocklibrary.PolarClockView;

import java.util.Calendar;

public class HoursArc extends Arc {
    private int currentHour;

    public HoursArc(PolarClockView clockView, int radius, int arcColor) {
        super(clockView, radius, arcColor);
        ArcOffsetMultiple = 3;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentHour = currentDateTime.getTime().getHours();
        float dayPercentComplete = (float) currentHour / 24;
        newSweepAngle = dayPercentComplete * 360;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = (currentHour + " HOURS").toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }

}
