package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.support.annotation.ColorInt;

import java.util.Calendar;

public class SecondsArc extends Arc {
    private int currentSecond;

    public SecondsArc(int radius, @ColorInt int arcColor, boolean showArcText) {
        super(radius, arcColor, showArcText);
        ArcOffsetFactor = 5;
        RectangleOffset = ArcOffsetFactor * ArcOffsetConstant;
        arcType = ArcTypes.Seconds;
    }

    @Override
    public void updateCurrentTime(Calendar currentDateTime) {
        currentSecond = currentDateTime.getTime().getSeconds();
        float minutePercentComplete = (float) currentSecond / 60;
        newSweepAngle = minutePercentComplete * MaxArcSweepAngle;
        arcText.UpdateText(currentSecond + " SECONDS");
    }

    @Override
    public void draw(Canvas canvas, int viewHeightMidpoint, int viewWidthMidpoint) {
        if (currentSweepAngle != newSweepAngle) {
            CalculateArcParameters(viewHeightMidpoint, viewWidthMidpoint);
            arcText.UpdateLength();
        }
        canvas.drawArc(rect, ArcStartingAngle, currentSweepAngle, false, arcPaint);
        arcText.Draw(canvas);
    }
}
