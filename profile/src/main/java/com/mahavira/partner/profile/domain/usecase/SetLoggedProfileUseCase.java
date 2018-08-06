package com.mahavira.partner.profile.domain.usecase;

import com.mahavira.partner.base.core.CompletableUseCase;
import com.mahavira.partner.profile.domain.entity.Partner;
import com.mahavira.partner.profile.domain.repo.ProfileLoggedRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 06/08/18.
 */

public class SetLoggedProfileUseCase implements CompletableUseCase<Partner> {

    private ProfileLoggedRepository mRepository;

    @Inject
    public SetLoggedProfileUseCase(ProfileLoggedRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(Partner param) throws Exception {
        return mRepository.setLoggedProfileEmail(param);
    }
}
