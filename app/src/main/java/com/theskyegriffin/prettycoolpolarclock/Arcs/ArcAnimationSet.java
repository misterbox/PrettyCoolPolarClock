package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;

import java.util.ArrayList;

public class ArcAnimationSet {
    private final ArcDrawer drawer;
    private final ArrayList<Arc> arcs;
    private final ArrayList<ValueAnimator> animators;
    private final SparseBooleanArray animatorsCompletedState;

    public ArcAnimationSet(ArcDrawer drawer) {
        this.drawer = drawer;
        animators = new ArrayList<ValueAnimator>();
        arcs = new ArrayList<Arc>();
        animatorsCompletedState = new SparseBooleanArray();
    }

    public void add(ValueAnimator animator, final Arc arc) {
        animators.add(animator);
        arcs.add(arc);
        animatorsCompletedState.append(animator.hashCode(), false);
        final ArcAnimationSet self = this;

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentSweepAngle = (float) animation.getAnimatedValue();
                arc.setCurrentSweepAngle(currentSweepAngle);
                self.onAnimationUpdate(animation);
            }
        });
    }

    public void start() {
        for (ValueAnimator animator : animators) {
            animator.start();
        }
    }

    public void stop() {
        for (ValueAnimator animator : animators) {
            animator.cancel();
        }
    }

    public void resume() {
        for (ValueAnimator animator : animators) {
            animator.resume();
        }
    }

    private void onAnimationUpdate(ValueAnimator animator) {
        animator.pause();
        animatorsCompletedState.put(animator.hashCode(), true);

        if (currentAnimationFrameComplete()) {
            drawer.drawArcs(arcs);
            resetCompletedState();
            resume();
        }
    }

    private boolean currentAnimationFrameComplete() {
        boolean frameComplete = true;

        for (int i = 0; i < animatorsCompletedState.size(); i++) {
            frameComplete = frameComplete && animatorsCompletedState.valueAt(i);
        }

        return frameComplete;
    }

    private void resetCompletedState() {
        for (int i = 0; i < animatorsCompletedState.size(); i++) {
            int animatorHash = animatorsCompletedState.keyAt(i);
            animatorsCompletedState.put(animatorHash, false);
        }
    }
}
