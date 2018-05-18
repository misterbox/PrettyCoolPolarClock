package com.theskyegriffin.prettycoolpolarclock.Utilities;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ColorAnalyzer {
    private static int brightnessThreshold = 123;

    public static boolean isColorBright(@ColorInt int color) {
        float brightnessIndex = getBrightnessIndex(color);

        return brightnessIndex > brightnessThreshold;
    }

    private static float getBrightnessIndex(@ColorInt int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int redConstant = 299;
        int greenConstant = 587;
        int blueConstant = 114;

        return (float) (red * redConstant + green * greenConstant + blue * blueConstant) / 1000;
    }
}
