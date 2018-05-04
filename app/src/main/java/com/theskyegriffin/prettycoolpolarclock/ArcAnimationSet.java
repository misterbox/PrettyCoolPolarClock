package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                this.onAnimationUpdate(animation);
                float currentSweepAngle = (float) animation.getAnimatedValue();
                arc.setCurrentSweepAngle(currentSweepAngle);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void resume() {
        for (ValueAnimator animator : animators) {
            animator.resume();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void pause() {
        for (ValueAnimator animator : animators) {
            animator.pause();
        }
    }

    // on animation update
    //  pause animation
    //  if all steps for current animation frame is complete, redraw canvas and start next frame
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onAnimationUpdate(ValueAnimator animator) {
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
