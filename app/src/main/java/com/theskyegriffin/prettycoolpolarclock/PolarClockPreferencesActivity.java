package com.theskyegriffin.prettycoolpolarclock;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class PolarClockPreferencesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        Preference circlePreference = getPreferenceScreen().findPreference("numberOfCircles");
        circlePreference.setOnPreferenceChangeListener(numberCheckListener);
    }

    Preference.OnPreferenceChangeListener numberCheckListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (newValue != null && newValue.toString().length() > 0
                    && newValue.toString().matches("\\d*")) {
                return true;
            }

            Toast.makeText(PolarClockPreferencesActivity.this, "Invalid Input", Toast.LENGTH_LONG)
                    .show();

            return false;
        }
    };
}
