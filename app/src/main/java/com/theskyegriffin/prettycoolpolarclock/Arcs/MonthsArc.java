package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;
import java.util.Locale;

public class MonthsArc extends Arc {
    private String currentMonthDisplayName;
    private int currentMonth;

    public MonthsArc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        super(radius, arcColor, settings);

        arcType = ArcType.Months;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedMonth = currentDateTime.getTime().getMonth();

        if (updatedMonth != currentMonth) {
            currentMonth = updatedMonth;
            currentMonthDisplayName = currentDateTime.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US).toUpperCase();
            float yearPercentComplete = (float) (currentMonth + 1) / 12;
            newSweepAngle = yearPercentComplete * MaxArcSweepAngle;
            String text = currentMonthDisplayName == null ? "" : currentMonthDisplayName;
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
