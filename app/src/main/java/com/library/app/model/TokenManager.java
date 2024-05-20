package com.library.app.model;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String TOKEN_KEY = "token";

    private static TokenManager instance;
    private SharedPreferences sharedPreferences;

    private TokenManager(Context context) {
        sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager(context.getApplicationContext());
        }
        return instance;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.apply();
    }

}
