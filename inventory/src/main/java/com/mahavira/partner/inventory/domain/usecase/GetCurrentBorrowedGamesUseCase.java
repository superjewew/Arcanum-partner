package com.mahavira.partner.inventory.domain.usecase;

import com.mahavira.partner.base.core.BaseUseCaseWithParam;
import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 27/07/18.
 *
 */

public class GetCurrentBorrowedGamesUseCase implements BaseUseCaseWithParam<String, List<String>> {

    private InventoryRepository mRepository;

    @Inject
    public GetCurrentBorrowedGamesUseCase(InventoryRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<List<String>> execute(String param) {
        return mRepository.getPartnerBorrowedGames(param);
    }
}
