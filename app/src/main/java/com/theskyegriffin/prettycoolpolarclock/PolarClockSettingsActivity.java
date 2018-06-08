package com.theskyegriffin.prettycoolpolarclock;

import android.app.Activity;
import android.os.Bundle;

public class PolarClockSettingsActivity extends Activity {
    public static final String KEY_PREF_SET_WALLPAPER_BUTTON = "pref_setWallpaperButton";
    public static final String KEY_PREF_SHOW_ARC_TEXT = "pref_showArcText";
    public static final String KEY_PREF_ARC_TEXT_COLOR = "pref_arcTextColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
