package com.mahavira.partner.profile.domain.usecase;

import com.mahavira.partner.base.core.CompletableUseCase;
import com.mahavira.partner.profile.domain.repo.ProfileLoggedRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by norman on 06/08/18.
 */

public class SetLoggedProfileUseCase implements CompletableUseCase<String> {

    private ProfileLoggedRepository mRepository;

    @Inject
    public SetLoggedProfileUseCase(ProfileLoggedRepository repository) {
        mRepository = repository;
    }

    @Override
    public Completable execute(String email) throws Exception {
        return mRepository.setLoggedProfileEmail(email);
    }
}
