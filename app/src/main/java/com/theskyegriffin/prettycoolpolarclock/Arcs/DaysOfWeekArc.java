package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;
import java.util.Locale;

public class DaysOfWeekArc extends Arc {
    private String currentDayDisplayName;
    private int currentDay;

    public DaysOfWeekArc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        super(radius, arcColor, settings);
        ArcOffsetFactor = 2;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;
        arcType = ArcType.DaysOfWeek;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedDay = currentDateTime.getTime().getDay();

        if (updatedDay != currentDay) {
            currentDay = updatedDay;
            currentDayDisplayName = currentDateTime.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US).toUpperCase();
            float weekPercentComplete = (float) (currentDay + 1) / 7;
            newSweepAngle = weekPercentComplete * MaxArcSweepAngle;
            String text = currentDayDisplayName == null ? "" : currentDayDisplayName;
            arcText.updateText(text);
        }
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            arcText.updateLength();
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        arcText.draw(canvas);
    }
}
