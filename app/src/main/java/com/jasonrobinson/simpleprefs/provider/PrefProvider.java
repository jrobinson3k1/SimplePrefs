package com.jasonrobinson.simpleprefs.provider;

import android.content.SharedPreferences;

public interface PrefProvider<T> {

    public void put(SharedPreferences prefs, String key, T value);

    public T get(SharedPreferences prefs, String key, T defaultValue);
}
