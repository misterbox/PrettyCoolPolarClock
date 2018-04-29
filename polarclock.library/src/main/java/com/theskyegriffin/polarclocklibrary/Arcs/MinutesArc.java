package com.theskyegriffin.polarclocklibrary.Arcs;

import android.graphics.Canvas;

import com.theskyegriffin.polarclocklibrary.PolarClockView;

import java.util.Calendar;

public class MinutesArc extends Arc {
    private int currentMinute;

    public MinutesArc(PolarClockView clockView, int radius, int arcColor) {
        super(clockView, radius, arcColor);
        ArcOffsetMultiple = 4;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentMinute = currentDateTime.getTime().getMinutes();
        float hourPercentComplete = (float) currentMinute / 60;
        newSweepAngle = hourPercentComplete * 360;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = (currentMinute + " MINUTES").toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
