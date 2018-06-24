package com.theskyegriffin.prettycoolpolarclock;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.TestArcFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class PolarClockEngineTests {
    TestArcFactory arcFactory;

    @Before
    public void Setup() {
    }

    @Test
    public void updateCurrentTime_givenUpdatedTime_updatesTimeOnArcs() {
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        SecondsArc secondsArc = new SecondsArc();
        arcs.add(secondsArc);
        arcFactory = new TestArcFactory(arcs);
        PolarClockEngine engine = new PolarClockEngine(arcFactory);
        Calendar currentTime = Calendar.getInstance();

        engine.updateCurrentTime(currentTime);

        assertEquals(currentTime, secondsArc.getCurrentTime());
    }
}
