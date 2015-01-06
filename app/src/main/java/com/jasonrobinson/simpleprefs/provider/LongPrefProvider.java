package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public class LongPrefProvider implements PrefProvider<Long> {

    @Override
    public void put(SharedPreferences prefs, String key, Long value) {
        prefs.edit().putLong(key, value).apply();
    }

    @Override
    public Long get(SharedPreferences prefs, String key, Long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }
}
