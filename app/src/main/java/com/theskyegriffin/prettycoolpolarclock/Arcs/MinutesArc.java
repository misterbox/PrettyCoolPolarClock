package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import com.theskyegriffin.prettycoolpolarclock.PolarClockSettings;

import java.util.Calendar;

public class MinutesArc extends Arc {
    private int currentMinute;

    public MinutesArc(int radius, @ColorInt int arcColor, PolarClockSettings settings) {
        super(radius, arcColor, settings);
        ArcOffsetFactor = 4;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;
        arcType = ArcType.Minutes;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedMinute = currentDateTime.getTime().getMinutes();

        if (updatedMinute != currentMinute) {
            currentMinute = updatedMinute;
            float hourPercentComplete = (float) currentMinute / 60;
            newSweepAngle = hourPercentComplete * MaxArcSweepAngle;
            arcText.updateText(currentMinute + " MINUTES");
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
