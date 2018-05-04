package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PrettyCoolPolarClockService.PolarClockWallpaperEngine;

import java.util.Calendar;

public class SecondsArc extends Arc {
    private int currentSecond;

    public SecondsArc(PolarClockWallpaperEngine wallpaperEngine, int radius, @ColorInt int arcColor) {
        super(wallpaperEngine, radius, arcColor);
        ArcOffsetMultiple = 5;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentSecond = currentDateTime.getTime().getSeconds();
        float minutePercentComplete = (float) currentSecond / 60;
        newSweepAngle = minutePercentComplete * MaxArcSweepAngle;
//        currentSweepAngle = minutePercentComplete * MaxArcSweepAngle;
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
