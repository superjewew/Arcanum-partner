package com.mahavira.partner.profile.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
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

    private FirebaseFirestore mInstance;

    @Inject
    public ProfileRepoImpl(FirebaseFirestore firestore) {
        mInstance = firestore;
    }

    @Override
    public Single<Partner> getProfileByUsername(String username) {
        return getValue(mInstance.collection("partner").document(username), Partner.class).toSingle();
    }

    @Override
    public Completable updateProfile(Partner partner) {
        return null;
    }

    @NonNull
    private <T> Maybe<T> getValue(@NonNull final DocumentReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObject(clazz)))
                        .addOnFailureListener(e::onError));
    }
}
