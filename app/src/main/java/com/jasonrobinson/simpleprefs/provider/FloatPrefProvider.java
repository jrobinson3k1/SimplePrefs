package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public class FloatPrefProvider implements PrefProvider<Float> {

    @Override
    public void put(SharedPreferences prefs, String key, Float value) {
        prefs.edit().putFloat(key, value).apply();
    }

    @Override
    public Float get(SharedPreferences prefs, String key, Float defaultValue) {
        return prefs.getFloat(key, defaultValue);
    }
}
