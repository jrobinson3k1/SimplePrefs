package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public class BooleanPrefProvider implements PrefProvider<Boolean> {

    @Override
    public void put(SharedPreferences prefs, String key, Boolean value) {
        prefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public Boolean get(SharedPreferences prefs, String key, Boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }
}
