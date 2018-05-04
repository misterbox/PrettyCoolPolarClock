package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.SparseBooleanArray;

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

    public void add(ValueAnimator animator) {
        animators.add(animator);
        animatorsCompleteState.append(animator.hashCode(), false);
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
        }
    }

    private boolean currentAnimationFrameComplete() {
        boolean frameComplete = false;

    }
}
