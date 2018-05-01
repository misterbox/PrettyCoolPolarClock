package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;

import java.util.Calendar;

public class MinutesArc extends Arc {
    private int currentMinute;

    public MinutesArc(int radius, int arcColor) {
        super(radius, arcColor);
        ArcOffsetMultiple = 4;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentMinute = currentDateTime.getTime().getMinutes();
        float hourPercentComplete = (float) currentMinute / 60;
//        newSweepAngle = hourPercentComplete * 360;
        currentSweepAngle = hourPercentComplete * 360;
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
