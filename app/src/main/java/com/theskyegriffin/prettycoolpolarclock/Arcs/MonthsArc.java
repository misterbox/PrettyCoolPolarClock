package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PrettyCoolPolarClockService.PolarClockWallpaperEngine;

import java.util.Calendar;
import java.util.Locale;

public class MonthsArc extends Arc {
    private String currentMonthDisplayName;

    public MonthsArc(PolarClockWallpaperEngine wallpaperEngine, int radius, @ColorInt int arcColor) {
        super(wallpaperEngine, radius, arcColor);
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int currentMonth = currentDateTime.getTime().getMonth();
        currentMonthDisplayName = currentDateTime.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US).toUpperCase();
        float yearPercentComplete = (float) (currentMonth + 1) / 12;
//        newSweepAngle = yearPercentComplete * 360;
        currentSweepAngle = yearPercentComplete * MaxArcSweepAngle;
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        setCanvasRectangle(viewHeightMidpoint, viewWidthMidpoint);
        resetTextPath();
        char[] text = currentMonthDisplayName == null ? new char[]{} : currentMonthDisplayName.toCharArray();
        int textLength = getTextPathLength(text);
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        canvas.drawTextOnPath(text, 0, textLength, textPath, 0, 12, textPaint);
    }
}
