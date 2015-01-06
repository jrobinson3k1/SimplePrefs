package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public class IntPrefProvider implements PrefProvider<Integer> {

    @Override
    public void put(SharedPreferences prefs, String key, Integer value) {
        prefs.edit().putInt(key, value).apply();
    }

    @Override
    public Integer get(SharedPreferences prefs, String key, Integer defaultValue) {
        return prefs.getInt(key, defaultValue);
    }
}
