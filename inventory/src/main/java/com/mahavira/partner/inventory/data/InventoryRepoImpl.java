package com.mahavira.partner.inventory.data;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.entity.Partner;
import com.mahavira.partner.inventory.domain.entity.ReturnRequest;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by norman on 27/07/18.
 *
 */

public class InventoryRepoImpl implements InventoryRepository {

    private static final String PRODUCT_COLLECTION = "products";

    private static final String PARTNER_COLLECTION = "partner";

    private static final String RETURN_REQUEST_COLLECTION = "return_request";

    private FirebaseFirestore mInstance;

    @Inject
    public InventoryRepoImpl(FirebaseFirestore firestore) {
        mInstance = firestore;
    }

    @Override
    public Single<List<String>> getPartnerBorrowedGames(String email) {
        Single<Partner> partner = getValue(
                mInstance.collection(PARTNER_COLLECTION).document(email), Partner.class).toSingle();
        return partner.map(Partner::getBorrowedGames);
    }

    @Override
    public Completable returnGames(ReturnRequest request) {
        return createReturnRequest(mInstance.collection(RETURN_REQUEST_COLLECTION), request);
    }

    @Override
    public Single<Boardgame> getProductByName(String param) {
        return getValue(mInstance.collection(PRODUCT_COLLECTION).document(param), Boardgame.class)
                .toSingle();
    }

    @NonNull
    private <T> Maybe<T> getValue(@NonNull final DocumentReference ref, Class<T> clazz) {
        return Maybe.create(
                e -> ref.get()
                        .addOnCompleteListener(task -> e.onSuccess(task.getResult().toObject(clazz)))
                        .addOnFailureListener(e::onError));
    }

    @NonNull
    private Completable createReturnRequest(@NonNull final CollectionReference ref, final ReturnRequest value) {
        return Completable.create(
                e -> {
                    DocumentReference docRef = ref.document();
                    value.setId(docRef.getId());
                    docRef.set(value)
                    .addOnSuccessListener(documentReference -> e.onComplete())
                    .addOnFailureListener(e::onError);
                });
    }
}
