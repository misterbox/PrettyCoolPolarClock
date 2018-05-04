package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PrettyCoolPolarClockService.PolarClockWallpaperEngine;

import java.util.Calendar;

public class HoursArc extends Arc {
    private int currentHour;

    public HoursArc(PolarClockWallpaperEngine wallpaperEngine, int radius, @ColorInt int arcColor) {
        super(wallpaperEngine, radius, arcColor);
        ArcOffsetMultiple = 3;
        RectangleOffset = ArcOffsetMultiple * ArcOffsetConstant;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentHour = currentDateTime.getTime().getHours();
        float dayPercentComplete = (float) currentHour / 24;
//        newSweepAngle = dayPercentComplete * 360;
        currentSweepAngle = dayPercentComplete * MaxArcSweepAngle;
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
