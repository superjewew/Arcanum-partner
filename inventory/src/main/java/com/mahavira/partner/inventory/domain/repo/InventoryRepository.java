package com.mahavira.partner.inventory.domain.repo;

import com.mahavira.partner.inventory.domain.entity.ReturnRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 27/07/18.
 *
 */

public interface InventoryRepository {
    Single<List<String>> getPartnerBorrowedGames(String email);

    Completable returnGames(ReturnRequest request);
}
