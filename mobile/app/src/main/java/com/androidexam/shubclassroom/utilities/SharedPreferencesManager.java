package com.androidexam.shubclassroom.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_ROLE = "role";

    private static SharedPreferencesManager instance;
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
    }

    public void setRole(String role) {
        sharedPreferences.edit().putString(KEY_ROLE, role).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }
    public String getRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

}
