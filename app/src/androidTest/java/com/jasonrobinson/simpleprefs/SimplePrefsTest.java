package com.jasonrobinson.simpleprefs;

import android.content.SharedPreferences;
import android.test.AndroidTestCase;

import com.jasonrobinson.simpleprefs.provider.PrefProvider;

public class SimplePrefsTest extends AndroidTestCase {

    private SimplePrefs mPrefs;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mPrefs = new SimplePrefs(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        mPrefs.removeAll();
        super.tearDown();
    }

    public void testStringProviderValue() throws Exception {
        mPrefs.put("key", "value");
        String value = mPrefs.get("key", null, String.class);
        assertEquals("value", value);
    }

    public void testMissingProvider() throws Exception {
        try {
            mPrefs.put("key", new Dummy("value"));
            fail("Expected exception was not thrown.");
        } catch (IllegalStateException e) {
            // pass
        }
    }

    public void testStringProviderDefaultValue() throws Exception {
        String value = mPrefs.get("key", "defaultValue", String.class);
        assertEquals("defaultValue", value);
    }

    public void testDummyValue() throws Exception {
        mPrefs.registerProvider(new DummyPrefProvider(), Dummy.class);
        mPrefs.put("key", new Dummy("value"));

        Dummy dummy = mPrefs.get("key", null, Dummy.class);
        assertEquals("value", dummy.text);
    }

    private class Dummy {

        String text;

        public Dummy(String text) {
            this.text = text;
        }
    }

    private class DummyPrefProvider implements PrefProvider<Dummy> {

        @Override
        public void put(SharedPreferences prefs, String key, Dummy value) {
            prefs.edit().putString(key, value.text).apply();
        }

        @Override
        public Dummy get(SharedPreferences prefs, String key, Dummy defaultValue) {
            String text = prefs.getString(key, null);
            if (text == null) {
                return defaultValue;
            }

            return new Dummy(text);
        }
    }
}
