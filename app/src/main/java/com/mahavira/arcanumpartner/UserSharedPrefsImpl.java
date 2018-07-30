package com.mahavira.arcanumpartner;

import android.content.Context;
import android.content.SharedPreferences;

import com.mahavira.partner.base.prefs.UserSharedPrefs;

import javax.inject.Inject;

/**
 * Created by norman on 31/07/18.
 */

public class UserSharedPrefsImpl implements UserSharedPrefs {

    private static final String USER_PREFS_FILE_KEY = "UserSharedPreferences";

    private final String EMAIL_PREFS_KEY = "loggedEmail";

    private SharedPreferences mSharedPref;

    @Inject
    public UserSharedPrefsImpl(Context context) {
        mSharedPref = context.getSharedPreferences(USER_PREFS_FILE_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public String getLoggedUserEmail() {
        return mSharedPref.getString(EMAIL_PREFS_KEY, "null");
    }

    @Override
    public void setLoggedUserEmail(String email) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(EMAIL_PREFS_KEY, email);
        editor.apply();
    }
}
