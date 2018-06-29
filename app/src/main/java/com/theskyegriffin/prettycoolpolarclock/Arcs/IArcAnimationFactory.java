package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.animation.ValueAnimator;

public interface IArcAnimationFactory {
    public ValueAnimator build(Arc arc);
}
