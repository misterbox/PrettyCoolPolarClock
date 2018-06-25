package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;

import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawable;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class ArcInstrumentationTests {
    @Test
    public void getDrawable_GivenCurrentTime_ReturnsDrawable_WithExpectedDimensions() {
        Calendar currentTime = Calendar.getInstance();
        float viewHeightMidpoint = 50;
        float viewWidthMidpoint = 50;
        float radius = 50;
        float expectedRectLeftPoint = 0;
        float expectedRectTopPoint = 0;
        float expectedRectRightPoint = 100;
        float expectedRectBottomPoint = 100;
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);

        arc.setCurrentTime(currentTime);
        ArcDrawable drawable = arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint);

        assertEquals(expectedRectLeftPoint, drawable.rect.left, 0);
        assertEquals(expectedRectTopPoint, drawable.rect.top, 0);
        assertEquals(expectedRectRightPoint, drawable.rect.right, 0);
        assertEquals(expectedRectBottomPoint, drawable.rect.bottom, 0);
    }
}
