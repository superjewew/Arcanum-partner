package com.mahavira.partner.profile.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.mahavira.partner.profile.domain.repo.ProfileLoggedRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 06/08/18.
 */

public class ProfileLoggedRepositoryImpl implements ProfileLoggedRepository {

    private static final String PROFILE_PREFS_FILE_KEY = "ProfileSharedPreferences";
    public static final String LOGGED_EMAIL = "loggedName";

    private SharedPreferences mSharedPref;

    @Inject
    public ProfileLoggedRepositoryImpl(Context context) {
        mSharedPref = context.getSharedPreferences(PROFILE_PREFS_FILE_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public Completable setLoggedProfileEmail(String email) {
        return Completable.create(emitter -> {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putString(LOGGED_EMAIL, email);
            editor.apply();
            emitter.onComplete();
        });
    }

    @Override
    public Single<String> getLoggedProfileEmail() {
        return getValue(mSharedPref).toSingle();
    }

    @NonNull
    private Maybe<String> getValue(@NonNull final SharedPreferences ref) {
        return Maybe.create(
                e -> e.onSuccess(ref.getString(LOGGED_EMAIL, "null")));
    }
}
