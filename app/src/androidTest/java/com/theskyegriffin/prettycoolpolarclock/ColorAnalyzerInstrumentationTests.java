package com.theskyegriffin.prettycoolpolarclock;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.test.runner.AndroidJUnit4;

import com.theskyegriffin.prettycoolpolarclock.Utilities.ColorAnalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ColorAnalyzerInstrumentationTests {
    @Test
    public void isColorBrightnessAboveThreshold_GivenColorOfBlack_ReturnsFalse() {
        @ColorInt int colorBlack = Color.BLACK;

        boolean result = ColorAnalyzer.isColorBright(colorBlack);

        assertFalse(result);
    }

    @Test
    public void isColorBrightnessAboveThreshold_GivenColorOfWhite_ReturnsTrue() {
        @ColorInt int colorWhite = Color.GREEN;

        boolean result = ColorAnalyzer.isColorBright(colorWhite);

        assertTrue(result);
    }
}
