package com.mahavira.partner.inventory.domain.repo;

import com.mahavira.partner.inventory.domain.entity.Boardgame;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by norman on 27/07/18.
 *
 */

public interface InventoryRepository {
    Single<List<Boardgame>> getPartnerBorrowedGames(String email);
}
