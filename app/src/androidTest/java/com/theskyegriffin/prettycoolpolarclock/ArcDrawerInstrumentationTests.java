package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;
import android.view.SurfaceHolder;

import com.theskyegriffin.prettycoolpolarclock.Arcs.Arc;
import com.theskyegriffin.prettycoolpolarclock.Arcs.ArcDrawer;
import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ArcDrawerInstrumentationTests {
    @Test
    public void drawArcs_GivenSecondsArc_DrawsArcWithExpectedProperties() {
        float radius = 50;
        float viewHeight = 100;
        float viewWidth = 100;
        SecondsArc arc = new SecondsArc(radius, Color.BLACK);
        ArrayList<Arc> arcs = new ArrayList<Arc>();
        arcs.add(arc);
        Canvas canvas = Mockito.mock(Canvas.class);
        SurfaceHolder holder = Mockito.mock(SurfaceHolder.class);
        ArcDrawer drawer = new ArcDrawer();
        drawer.setSurfaceHolder(holder);
        drawer.setViewDimensions(viewHeight, viewWidth);
        when(holder.lockCanvas()).thenReturn(canvas);

        drawer.drawArcs(arcs);

        verify(canvas).drawArc(any(), eq(arc.getStartAngle()), eq(arc.getCurrentSweepAngle()), eq(false), any());
    }
}
