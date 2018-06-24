package com.theskyegriffin.prettycoolpolarclock;

import android.view.SurfaceHolder;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcFactory;
import com.theskyegriffin.prettycoolpolarclock.Arcs.IArcFactory;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClockEngine {
    private IArcFactory arcFactory;
    private SurfaceHolder surfaceHolder;
    private Calendar currentTime;
    private ArrayList<Arc> arcs;

    public PolarClockEngine() {
        this(new ArcFactory());
    }

    public PolarClockEngine(IArcFactory arcFactory) {
        this.arcFactory = arcFactory;
        arcs = this.arcFactory.buildArcs();
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void updateCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
        onUpdateCurrentTime();
    }

    private void onUpdateCurrentTime() {
        for (Arc arc : arcs) {
            arc.setCurrentTime(currentTime);
        }
    }
}
