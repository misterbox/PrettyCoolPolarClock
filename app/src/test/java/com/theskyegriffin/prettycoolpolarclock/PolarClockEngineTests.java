package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawer;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.TestArcFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class PolarClockEngineTests {
    TestArcFactory arcFactory;

    @Test
    public void updateCurrentTime_givenUpdatedTime_updatesTimeOnArcs() {
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        SecondsArc secondsArc = new SecondsArc(0, Color.BLACK);
        arcs.add(secondsArc);
        arcFactory = new TestArcFactory(arcs);
        PolarClockEngine engine = new PolarClockEngine(arcFactory, new ArcDrawer());
        Calendar currentTime = Calendar.getInstance();

        engine.updateCurrentTime(currentTime);

        assertEquals(currentTime, secondsArc.getCurrentTime());
    }
}
