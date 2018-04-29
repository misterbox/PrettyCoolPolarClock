package com.theskyegriffin.polarclocklibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.theskyegriffin.polarclocklibrary.Utilities.ColorAnalyzer;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
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
