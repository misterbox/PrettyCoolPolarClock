package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.animation.ValueAnimator;

public class ArcAnimationFactory implements IArcAnimationFactory {
    @Override
    public ValueAnimator build(Arc arc) {
        return ValueAnimator.ofFloat(arc.getCurrentSweepAngle(), arc.getUpdatedSweepAngle()).setDuration(500);
    }
}
