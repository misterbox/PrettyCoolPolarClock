package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.test.runner.AndroidJUnit4;

import com.theskyegriffin.prettycoolpolarclock.Arcs.SecondsArc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class ArcTextInstrumentationTests {
    @Test
    public void secondsArc_GivenArcColorOfWhite_AndArcTextColorSettingOfDynamic_SetsArcTextColorToBlack() {
        PolarClockSettings settings = new PolarClockSettings(true, "dynamic");
        @ColorInt int arcColor = Color.WHITE;
        SecondsArc arc = new SecondsArc(1, arcColor, settings);
        Canvas canvas = Mockito.mock(Canvas.class);

        arc.draw(canvas, 100, 100);

        verify(canvas).drawTextOnPath(any(), anyInt(), anyInt(), any(), anyFloat(), anyFloat(), argThat(paint -> paint.getColor() == Color.BLACK));
    }
}
