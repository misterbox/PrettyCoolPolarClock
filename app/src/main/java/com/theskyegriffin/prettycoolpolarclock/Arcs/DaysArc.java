package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PolarClockSettings;

import java.util.Calendar;

public class DaysArc extends Arc {
    private int currentDate;

    public DaysArc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        super(radius, arcColor, settings);
        ArcOffsetFactor = 1;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;

        arcType = ArcType.Days;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedDate = currentDateTime.getTime().getDate();

        if (updatedDate != currentDate) {
            currentDate = updatedDate;
            int daysInMonth = currentDateTime.getActualMaximum(Calendar.DAY_OF_MONTH);
            float monthPercentComplete = (float) currentDate / daysInMonth;
            newSweepAngle = monthPercentComplete * MaxArcSweepAngle;
            arcText.updateText("DAY " + currentDate);
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
