package net.harimurti.bluetooth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private static final String OVERRIDE_PAIRING_REQUEST = "OVERRIDE_PAIRING_REQUEST";
    private static final String LAST_REQUESTED_DEVICE = "LAST_REQUESTED_DEVICE";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isOverridePairingRequest() {
        return preferences.getBoolean(OVERRIDE_PAIRING_REQUEST, false);
    }

    public void setOverridePairingRequest(boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(OVERRIDE_PAIRING_REQUEST, value);
        editor.apply();
    }

    public String getLastRequested() {
        return preferences.getString(LAST_REQUESTED_DEVICE, "None");
    }

    public void setLastRequested(String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LAST_REQUESTED_DEVICE, value);
        editor.apply();
    }
}
