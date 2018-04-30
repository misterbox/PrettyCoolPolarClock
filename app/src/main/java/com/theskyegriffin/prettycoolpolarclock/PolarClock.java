package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.DaysOfWeekArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.HoursArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MinutesArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.MonthsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClock {
    private ArrayList<Arc> arcs;

    public PolarClock(int radius) {
        arcs = new ArrayList<Arc>();
        arcs.add(new SecondsArc(radius, Color.BLACK));
        arcs.add(new MinutesArc(radius, Color.BLACK));
        arcs.add(new HoursArc(radius, Color.BLACK));
        arcs.add(new DaysOfWeekArc(radius, Color.BLACK));
        arcs.add(new DaysArc(radius, Color.BLACK));
        arcs.add(new MonthsArc(radius, Color.BLACK));
    }

    public void updateCurrentTime(Calendar currentDateTime) {
        for (Arc arc : arcs) {
            arc.updateCurrentTime(currentDateTime);
        }
    }

//    public void startAnimation() {
//        for (Arc arc : arcs) {
//            arc.startAnimation();
//        }
//    }
}
