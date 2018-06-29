package com.theskyegriffin.prettycoolpolarclock.Settings;

public class PolarClockSettings {
    public boolean showArcText;
//    private ArcTextColorSetting arcTextColorSetting;

    public PolarClockSettings(boolean showArcText, String arcTextColorSettingString) {
        this.showArcText = showArcText;
//        setArcTextColorSetting(arcTextColorSettingString);
    }

//    public ArcTextColorSetting getArcTextColorSetting() {
//        return arcTextColorSetting;
//    }
//
//    private void setArcTextColorSetting(String arcTextColorSettingString) {
//        this.arcTextColorSetting = parseArcTextColorSetting(arcTextColorSettingString);
//    }
//
//    private ArcTextColorSetting parseArcTextColorSetting(String arcTextColorSettingString) {
//        ArcTextColorSetting result = ArcTextColorSetting.WHITE;
//
//        try {
//            result = ArcTextColorSetting.valueOf(arcTextColorSettingString.toUpperCase(Locale.ENGLISH));
//        }
//        catch (IllegalArgumentException e) {
//            Log.e("ARCSETTINGS", "Exception thrown parsing the value of the arc text color setting" + System.lineSeparator() +
//                    "Message: " + e.getMessage() + System.lineSeparator() +
//                    "Stacktrace: " + e.getStackTrace());
//        }
//
//        return result;
//    }
}
