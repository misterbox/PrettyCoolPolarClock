package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;

import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysOfWeekArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.HoursArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MinutesArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MonthsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArcTests {
    @Test
    public void givenSecondsArc_WithRadius400_SetsRectangleOffsetTo250() {
        SecondsArc arc = new SecondsArc(null, 400, Color.BLACK);
        float expectedOffset = 250;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }

    @Test
    public void givenMinutesArc_WithRadius400_SetsRectangleOffsetTo200() {
        MinutesArc arc = new MinutesArc(null, 400, Color.BLACK);
        float expectedOffset = 200;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }

    @Test
    public void givenHoursArc_WithRadius400_SetsRectangleOffsetTo150() {
        HoursArc arc = new HoursArc(null, 400, Color.BLACK);
        float expectedOffset = 150;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }

    @Test
    public void givenDaysOfWeekArc_WithRadius400_SetsRectangleOffsetTo100() {
        DaysOfWeekArc arc = new DaysOfWeekArc(null, 400, Color.BLACK);
        float expectedOffset = 100;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }

    @Test
    public void givenDaysArc_WithRadius400_SetsRectangleOffsetTo50() {
        DaysArc arc = new DaysArc(null, 400, Color.BLACK);
        float expectedOffset = 50;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }

    @Test
    public void givenMonthsArc_WithRadius300_SetsRectangleOffsetToZero() {
        MonthsArc arc = new MonthsArc(null, 300, Color.BLACK);
        float expectedOffset = 0;

        float actualOffset = arc.getRectangleOffset();

        assertEquals(expectedOffset, actualOffset, 0);
    }
}