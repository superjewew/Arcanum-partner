package com.mahavira.partner.profile.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.mahavira.partner.profile.domain.entity.Partner;
import com.mahavira.partner.profile.domain.repo.ProfileRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 23/07/18.
 *
 */

public class ProfileRepoImpl implements ProfileRepository {

    @Inject
    ProfileRepoImpl() {

    }

    @Override
    public Single<Partner> getProfileByUsername(String username) {
        return null;
    }

    @Override
    public Completable updateProfile(Partner partner) {
        return null;
    }

    @NonNull
    private <T> Maybe<List<T>> getValue(@NonNull final CollectionReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObjects(clazz)))
                        .addOnFailureListener(e::onError));
    }
}
