package com.idiscount.dfgden.idiscount.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String SHARED_PREFERENCE = "com.idiscount.dfgden.idiscount.prefer";

    public static final String KEY_PREFERENCE_TEMP = "KEY_PREFERENCE_TEMP";

    private SharedPreferences preferences;

    public PreferenceHelper(Context context) {
        this.preferences = context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return preferences.getInt(key, -1);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }
}