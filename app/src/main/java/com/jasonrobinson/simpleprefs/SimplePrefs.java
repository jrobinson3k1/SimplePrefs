package com.jasonrobinson.simpleprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.jasonrobinson.simpleprefs.provider.BooleanPrefProvider;
import com.jasonrobinson.simpleprefs.provider.FloatPrefProvider;
import com.jasonrobinson.simpleprefs.provider.IntPrefProvider;
import com.jasonrobinson.simpleprefs.provider.LongPrefProvider;
import com.jasonrobinson.simpleprefs.provider.PrefProvider;
import com.jasonrobinson.simpleprefs.provider.StringPrefProvider;
import com.jasonrobinson.simpleprefs.provider.StringSetPrefProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimplePrefs {

    private SharedPreferences mPrefs;

    private Map<Class<?>, PrefProvider<?>> mProviders = new HashMap<>();

    public SimplePrefs(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        registerDefaultProviders();
    }

    public SimplePrefs(Context context, String name) {
        mPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        registerDefaultProviders();
    }

    private void registerDefaultProviders() {
        registerProvider(new StringPrefProvider(), String.class);
        registerProvider(new BooleanPrefProvider(), Boolean.class);
        registerProvider(new FloatPrefProvider(), Float.class);
        registerProvider(new IntPrefProvider(), Integer.class);
        registerProvider(new LongPrefProvider(), Long.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            registerProvider(new StringSetPrefProvider(), Set.class);
        }
    }

    public <T> void put(String key, T obj) {
        PrefProvider<T> provider = (PrefProvider<T>) getProvider(obj.getClass());
        if (provider == null) {
            throw new IllegalStateException("Missing provider for " + obj.getClass().getName() + ".");
        }

        provider.put(mPrefs, key, obj);
    }

    public <T> T get(String key, T defaultValue, Class<?> valueClass) {
        PrefProvider<T> provider = (PrefProvider<T>) getProvider(valueClass);
        if (provider == null) {
            throw new IllegalStateException("Missing provider for " + defaultValue.getClass().getName() + ".");
        }

        return provider.get(mPrefs, key, defaultValue);
    }

    public void remove(String key) {
        mPrefs.edit().remove(key).apply();
    }

    public void removeAll() {
        mPrefs.edit().clear().apply();
    }

    public void registerProvider(PrefProvider<?> provider, Class<?> valueClass) {
        mProviders.put(valueClass, provider);
    }

    private PrefProvider<?> getProvider(Class<?> cls) {
        return mProviders.get(cls);
    }
}
