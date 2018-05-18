package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class DaysArc extends Arc {
    private int currentDate;

    public DaysArc(int radius, @ColorInt int arcColor, boolean showArcText) {
        super(radius, arcColor, showArcText);
        ArcOffsetFactor = 1;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;

        arcType = ArcTypes.Days;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedDate = currentDateTime.getTime().getDate();

        if (updatedDate != currentDate) {
            currentDate = updatedDate;
            int daysInMonth = currentDateTime.getActualMaximum(Calendar.DAY_OF_MONTH);
            float monthPercentComplete = (float) currentDate / daysInMonth;
            newSweepAngle = monthPercentComplete * MaxArcSweepAngle;
            arcText.UpdateText(currentDate + " DAYS");
        }
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            arcText.UpdateLength(rect, ArcStartingAngle, currentSweepAngle);
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        arcText.Draw(canvas);
    }
}
