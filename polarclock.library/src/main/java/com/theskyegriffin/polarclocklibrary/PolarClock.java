package com.theskyegriffin.polarclocklibrary;

import com.theskyegriffin.polarclocklibrary.Arcs.Arc;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClock {
    private ArrayList<Arc> arcs;

    public PolarClock() {

    }

    public void updateCurrentTime(Calendar currentDateTime) {
        for (Arc arc : arcs) {
            arc.updateCurrentTime(currentDateTime);
        }
    }

    public void startAnimation() {
        for (Arc arc : arcs) {
            arc.startAnimation();
        }
    }
}
