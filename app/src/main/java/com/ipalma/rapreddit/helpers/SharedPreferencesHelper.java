package com.ipalma.rapreddit.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ipalma.rapreddit.RRApplication;

/**
 * Helper Class to manage Shared Preferences in the app.
 * Just call the static methods to save or retrieve the data.
 * Created by ivan on 12/23/16.
 */
public class SharedPreferencesHelper {

    private static SharedPreferences sp = null;

    private static void initializeIfNeeded() {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(RRApplication.getAppContext());
        }
    }

    public static void removePreference(String key) {
        initializeIfNeeded();
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void savePreference(String key, String value) {
        initializeIfNeeded();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPreference(String key) {
        return getStringPreference(key, null);
    }

    public static String getStringPreference(String key, String defaultValue) {
        initializeIfNeeded();
        return sp.getString(key, defaultValue);
    }
}
