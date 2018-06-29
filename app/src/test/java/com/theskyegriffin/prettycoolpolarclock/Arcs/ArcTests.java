package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Color;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ArcTests {
    private final float radius = 50;

    @Test
    public void isReadyForDrawing_GivenSecondsArc_AndNoUpdatedTime_ShouldReturnFalse() {
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);

        boolean isReadyForDrawing = arc.isReadyForDrawing();

        assertFalse(isReadyForDrawing);
    }

    @Test
    public void isReadyForDrawing_GivenSecondsArc_AndUpdatedTime_ShouldReturnTrue() {
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);
        Calendar currentTime = Calendar.getInstance();
        arc.setCurrentTime(currentTime);

        boolean isReadyForDrawing = arc.isReadyForDrawing();

        assertTrue(isReadyForDrawing);
    }

    @Test
    public void setCurrentTime_GivenCurrentTime_SetsExpectedUpdatedSweepAngle() {
        float expectedSweepAngle = 180;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 1, 1, 0, 0, 30);
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);

        arc.setCurrentTime(calendar);

        assertEquals(expectedSweepAngle, arc.getUpdatedSweepAngle(), 1.0);
    }
}
