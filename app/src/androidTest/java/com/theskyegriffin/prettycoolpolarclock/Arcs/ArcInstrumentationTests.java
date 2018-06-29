package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ArcInstrumentationTests {
    private float viewHeightMidpoint = 50;
    private float viewWidthMidpoint = 50;
    private float radius = 50;

    @Test
    public void getDrawable_GivenSecondsArc_AndCurrentTime_ReturnsDrawable_WithExpectedDimensions() {
        Calendar currentTime = Calendar.getInstance();
        float RectangleOffset = ((float) 1 / 8) * radius * 5;
        float expectedRectLeftPoint = viewWidthMidpoint - radius + RectangleOffset;
        float expectedRectTopPoint = viewHeightMidpoint - radius + RectangleOffset;
        float expectedRectRightPoint = viewWidthMidpoint + radius - RectangleOffset;
        float expectedRectBottomPoint = viewHeightMidpoint + radius - RectangleOffset;
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);

        arc.setCurrentTime(currentTime);
        ArcDrawable drawable = arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint);

        assertEquals(expectedRectLeftPoint, drawable.rect.left, 0);
        assertEquals(expectedRectTopPoint, drawable.rect.top, 0);
        assertEquals(expectedRectRightPoint, drawable.rect.right, 0);
        assertEquals(expectedRectBottomPoint, drawable.rect.bottom, 0);
    }

    @Test
    public void getDrawable_GivenSecondsArc_AndCurrentTime_ReturnsDrawable_WithExpectedStartAngle_AndSweepAngle() {
        float expectedSweepAngle = 0;
        float expectedStartAngle = 270;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 1, 1, 0, 0, 0);
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);

        arc.setCurrentTime(calendar);
        ArcDrawable drawable = arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint);

        assertEquals(expectedStartAngle, drawable.startAngle, 0);
        assertEquals(expectedSweepAngle, drawable.sweepAngle, 0);
    }

    @Test
    public void getDrawable_GivenSecondsArc_AndArcColor_ReturnsDrawable_WithExpectedPaintColor() {
        @ColorInt int expectedColor = Color.RED;
        Calendar calendar = Calendar.getInstance();
        SecondsArc arc = new SecondsArc(radius, expectedColor);

        arc.setCurrentTime(calendar);
        ArcDrawable drawable = arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint);

        assertEquals(expectedColor, drawable.paint.getColor());
    }
}
