package com.mahavira.partner.profile.domain.usecase;

import com.mahavira.partner.base.core.BaseUseCaseWithParam;
import com.mahavira.partner.profile.domain.entity.Partner;
import com.mahavira.partner.profile.domain.repo.ProfileRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 23/07/18.
 */

public class GetProfileUseCase implements BaseUseCaseWithParam<String, Partner> {

    private ProfileRepository mRepository;

    @Inject
    GetProfileUseCase(ProfileRepository repository) {
        mRepository = repository;
    }

    @Override
    public Single<Partner> execute(String param) {
        return mRepository.getProfileByUsername(param);
    }
}
