package com.mahavira.partner.inventory.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partner.inventory.domain.entity.Boardgame;
import com.mahavira.partner.inventory.domain.entity.Partner;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 27/07/18.
 *
 */

public class InventoryRepoImpl implements InventoryRepository {

    private static final String PARTNER_COLLECTION = "partner";

    private FirebaseFirestore mInstance;

    @Inject
    public InventoryRepoImpl(FirebaseFirestore firestore) {
        mInstance = firestore;
    }

    @Override
    public Single<List<Boardgame>> getPartnerBorrowedGames(String email) {
        Single<Partner> partner = getValue(mInstance.collection(PARTNER_COLLECTION).document(email), Partner.class).toSingle();
        return partner.map(Partner::getBorrowedGames);
    }

    @NonNull
    private <T> Maybe<T> getValue(@NonNull final DocumentReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObject(clazz)))
                        .addOnFailureListener(e::onError));
    }
}
