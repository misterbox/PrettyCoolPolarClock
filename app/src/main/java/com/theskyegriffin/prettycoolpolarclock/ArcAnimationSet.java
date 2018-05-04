package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.PrettyCoolPolarClockService.PolarClockWallpaperEngine;

import java.util.ArrayList;

public class ArcAnimationSet {
    private final PolarClockWallpaperEngine wallpaperEngine;
    private ArrayList<ValueAnimator> animators;
    private SparseBooleanArray animatorsCompleteState;

    public ArcAnimationSet(PolarClockWallpaperEngine wallpaperEngine) {
        this.wallpaperEngine = wallpaperEngine;
        animators = new ArrayList<ValueAnimator>();
        animatorsCompleteState = new SparseBooleanArray();
    }

    public void add(ValueAnimator animator, final Arc arc) {
        animators.add(animator);
        animatorsCompleteState.append(animator.hashCode(), false);
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

    public void remove(ValueAnimator animator) {
        animators.remove(animator);
    }

    public void start(){
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

    public void pause() {
        for (ValueAnimator animator : animators) {
            animator.pause();
        }
    }

    private void onAnimationUpdate(ValueAnimator animator) {
        if (animators.contains(animator)) {
            animator.pause();
            animatorsCompleteState.put(animator.hashCode(), true);
        }

        if (currentAnimationFrameComplete()) {
            wallpaperEngine.draw();
            resetCompleteState();
            resume();
        }
    }

    private boolean currentAnimationFrameComplete() {
        boolean frameComplete = true;

        for (int i = 0; i < animatorsCompleteState.size(); i++) {
            frameComplete = frameComplete && animatorsCompleteState.valueAt(i);
        }

        return frameComplete;
    }

    private void resetCompleteState() {
        for (int i = 0; i < animatorsCompleteState.size(); i++) {
            int animatorHash = animatorsCompleteState.keyAt(i);
            animatorsCompleteState.put(animatorHash, false);
        }
    }
}
