package com.mahavira.partner.inventory.domain.usecase;

import com.mahavira.partner.base.core.BaseUseCaseWithParam;
import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetProductByNameUseCase implements BaseUseCaseWithParam<String, Boardgame> {

    private InventoryRepository mRepository;

    @Inject
    public GetProductByNameUseCase(InventoryRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<Boardgame> execute(String param) {
        return mRepository.getProductByName(param);
    }
}