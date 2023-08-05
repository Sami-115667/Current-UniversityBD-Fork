package com.techtravelcoder.universitybd.loginandsignup;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {
    private static final String PREFS_NAME = "user_prefs";
    private static final String KEY_IS_AUTHENTICATED = "is_authenticated";

    public static boolean isUserAuthenticated(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_IS_AUTHENTICATED, false);
    }

    public static void saveUserAuthentication(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_AUTHENTICATED, true);
        editor.apply();
    }

    public static void clearUserAuthentication(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_AUTHENTICATED, false);
        editor.apply();
    }

}
