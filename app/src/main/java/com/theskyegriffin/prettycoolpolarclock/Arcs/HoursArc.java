package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PolarClockSettings;

import java.util.Calendar;

public class HoursArc extends Arc {
    private int currentHour;

    public HoursArc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        super(radius, arcColor, settings);
        ArcOffsetFactor = 3;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;
        arcType = ArcType.Hours;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedHour = currentDateTime.getTime().getHours();

        if (updatedHour != currentHour) {
            currentHour = updatedHour;
            float dayPercentComplete = (float) currentHour / 24;
            newSweepAngle = dayPercentComplete * MaxArcSweepAngle;
            arcText.updateText(currentHour + " HOURS");
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
