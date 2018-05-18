package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class HoursArc extends Arc {
    private int currentHour;

    public HoursArc(int radius, @ColorInt int arcColor, boolean showArcText) {
        super(radius, arcColor, showArcText);
        ArcOffsetFactor = 3;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;
        arcType = ArcTypes.Hours;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        int updatedHour = currentDateTime.getTime().getHours();

        if (updatedHour != currentHour) {
            currentHour = updatedHour;
            float dayPercentComplete = (float) currentHour / 24;
            newSweepAngle = dayPercentComplete * MaxArcSweepAngle;
//            arcText = (currentHour + " HOURS").toCharArray();
            arcText.UpdateText(currentHour + " HOURS");
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
