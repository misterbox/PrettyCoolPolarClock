package com.theskyegriffin.prettycoolpolarclock;

import android.animation.ValueAnimator;
import android.graphics.Color;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcAnimationFactory;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawer;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.TestArcAnimationFactory;
import com.theskyegriffin.prettycoolpolarclock.Arcs.TestArcFactory;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PolarClockEngineTests {
    TestArcFactory arcFactory;

    @Test
    public void updateCurrentTime_GivenUpdatedTime_UpdatesTimeOnArcs() {
    }
}
