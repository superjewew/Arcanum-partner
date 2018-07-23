package com.mahavira.partner.profile.domain.repo;

import com.mahavira.partner.profile.domain.entity.Partner;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 23/07/18.
 *
 */

public interface ProfileRepository {
    Single<Partner> getProfileByUsername(String username);
    Completable updateProfile(Partner partner);
}
