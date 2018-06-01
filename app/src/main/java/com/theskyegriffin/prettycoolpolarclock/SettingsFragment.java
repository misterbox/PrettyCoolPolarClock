package com.theskyegriffin.prettycoolpolarclock;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);

        Preference button = findPreference(PolarClockSettingsActivity.KEY_PREF_SET_WALLPAPER_BUTTON);
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Context context = getActivity();
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);

                try {
                    wallpaperManager.clear();
                }
                catch (Exception e) {
                    Log.e("PolarClock.Settings", "Error when attempting to clear current wallpaper");
                }

                Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(context, PrettyCoolPolarClockService.class));
                intent.putExtra("SET_LOCKSCREEN_WALLPAPER", true);
                startActivity(intent);
                return true;
            }
        });
    }
}
