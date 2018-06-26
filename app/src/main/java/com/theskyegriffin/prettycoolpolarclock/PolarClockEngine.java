package com.theskyegriffin.prettycoolpolarclock;

import android.view.SurfaceHolder;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawable;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawer;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcFactory;
import com.theskyegriffin.prettycoolpolarclock.Arcs.IArcFactory;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClockEngine {
    private final ArcDrawer drawer;
    private IArcFactory arcFactory;
    private SurfaceHolder surfaceHolder;
    private Calendar currentTime;
    private ArrayList<Arc> arcs;

    public PolarClockEngine() {
        this(new ArcFactory(), new ArcDrawer());
    }

    public PolarClockEngine(IArcFactory arcFactory, ArcDrawer drawer) {
        this.arcFactory = arcFactory;
        arcs = this.arcFactory.buildArcs();
        this.drawer = drawer;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void updateCurrentTime(Calendar currentTime, int viewHeightMidpoint, int viewWidthMidpoint) {
        this.currentTime = currentTime;
        onUpdateCurrentTime(viewHeightMidpoint, viewWidthMidpoint);
    }

    private void onUpdateCurrentTime(int viewHeightMidpoint, int viewWidthMidpoint) {
        for (Arc arc : arcs) {
            arc.setCurrentTime(currentTime);
            ArcDrawable drawable = arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint);
            drawer.draw(drawable);
        }
    }
}
