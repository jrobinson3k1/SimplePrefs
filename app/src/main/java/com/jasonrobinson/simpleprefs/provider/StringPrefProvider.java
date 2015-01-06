package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public class StringPrefProvider implements PrefProvider<String> {

    @Override
    public void put(SharedPreferences prefs, String key, String data) {
        prefs.edit().putString(key, data).apply();
    }

    @Override
    public String get(SharedPreferences prefs, String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }
}
