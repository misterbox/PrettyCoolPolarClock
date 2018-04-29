package com.theskyegriffin.polarclocklibrary.Arcs;

import android.graphics.Canvas;

import com.theskyegriffin.polarclocklibrary.PolarClockView;

import java.util.Calendar;

public class SecondsArc extends Arc {
    private int currentSecond;

    public SecondsArc(PolarClockView clockView, int radius, int arcColor) {
        super(clockView, radius, arcColor);
        ArcOffsetMultiple = 5;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentSecond = currentDateTime.getTime().getSeconds();
        float minutePercentComplete = (float) currentSecond / 60;
        newSweepAngle = minutePercentComplete * 360;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = (currentSecond + " SECONDS").toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
