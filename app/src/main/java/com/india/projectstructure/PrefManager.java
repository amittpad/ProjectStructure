package com.india.projectstructure;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    /**
     * logged in user session data
     */

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "com.india.projectstructure";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public static String readSharedPreferencesData(Context ctx, String settingName, String defaultValue) {
        sharedPref = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    public static void saveSharedPreferencesData(Context ctx, String settingName, String settingValue) {
        sharedPref = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static void clearSharedPreferencesData(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        sharedPref.edit().clear().apply();
        sharedPref.edit().remove(Constant.KEY_USER_ID).commit();
    }


    public PrefManager(Context context) {
        this._context = context;
        sharedPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
