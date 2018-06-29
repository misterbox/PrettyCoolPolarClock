package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.view.SurfaceHolder;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcAnimationSet;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawer;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcFactory;
import com.theskyegriffin.prettycoolpolarclock.Arcs.IArcFactory;

import java.util.ArrayList;
import java.util.Calendar;

public class PolarClockEngine {
    private final ArcDrawer drawer;
    private IArcFactory arcFactory;
    private Calendar currentTime;
    private ArrayList<Arc> arcs;
    private ArcAnimationSet animationSet;

    public PolarClockEngine() {
        this(new ArcFactory(), new ArcDrawer());
    }

    public PolarClockEngine(IArcFactory arcFactory, ArcDrawer drawer) {
        this.arcFactory = arcFactory;
        arcs = this.arcFactory.buildArcs();
        this.drawer = drawer;
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        drawer.setSurfaceHolder(surfaceHolder);
    }

    public void setViewDimensions(float viewHeight, float viewWidth) {
        drawer.setViewDimensions(viewHeight, viewWidth);
    }

    public void updateCurrentTime(Calendar currentTime) {
        this.currentTime = currentTime;
        animationSet = new ArcAnimationSet(drawer);

        for (Arc arc : arcs) {
            arc.setCurrentTime(currentTime);

            if (arc.isReadyForDrawing()) {
                ValueAnimator animator = ValueAnimator.ofFloat(arc.getCurrentSweepAngle(), arc.getUpdatedSweepAngle()).setDuration(500);
                animationSet.add(animator, arc);
            }
        }

        animationSet.start();
    }

    public void stop() {
        if (animationSet != null) {
            animationSet.stop();
        }

        drawer.setSurfaceHolder(null);
    }
}
