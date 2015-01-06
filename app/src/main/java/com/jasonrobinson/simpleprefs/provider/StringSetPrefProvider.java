package com.jasonrobinson.simpleprefs.provider;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Set;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StringSetPrefProvider implements PrefProvider<Set<String>> {

    @Override
    public void put(SharedPreferences prefs, String key, Set<String> value) {
        prefs.edit().putStringSet(key, value).apply();
    }

    @Override
    public Set<String> get(SharedPreferences prefs, String key, Set<String> defaultValue) {
        return prefs.getStringSet(key, defaultValue);
    }
}
