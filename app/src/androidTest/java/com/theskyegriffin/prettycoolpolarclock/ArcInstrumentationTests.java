package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;

import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawable;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

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

}
