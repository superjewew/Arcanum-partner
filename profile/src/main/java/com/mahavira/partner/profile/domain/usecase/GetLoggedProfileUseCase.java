package com.mahavira.partner.profile.domain.usecase;

import com.mahavira.partner.base.core.BaseUseCase;
import com.mahavira.partner.profile.domain.entity.Partner;
import com.mahavira.partner.profile.domain.repo.ProfileLoggedRepository;
import com.mahavira.partner.profile.domain.repo.ProfileRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 06/08/18.
 */

public class GetLoggedProfileUseCase implements BaseUseCase<Partner> {

    private ProfileLoggedRepository mProfileLoggedRepository;

    private ProfileRepository mProfileRepository;

    @Inject
    public GetLoggedProfileUseCase(ProfileLoggedRepository repository, ProfileRepository profileRepository) {
        mProfileLoggedRepository = repository;
        mProfileRepository = profileRepository;
    }

    @Override
    public Single<Partner> execute() {
        return mProfileLoggedRepository.getLoggedProfileEmail()
                .flatMap(email -> mProfileRepository.getProfileByEmail(email));
    }
}
