package com.mahavira.partner.inventory.domain.usecase;

import com.mahavira.partner.base.core.CompletableUseCase;
import com.mahavira.partner.inventory.domain.entity.ReturnRequest;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 06/08/18.
 */

public class ReturnGamesUseCase implements CompletableUseCase<ReturnRequest> {

    private InventoryRepository mRepository;

    @Inject
    public ReturnGamesUseCase(InventoryRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(ReturnRequest param) {
        return mRepository.returnGames(param);
    }
}
