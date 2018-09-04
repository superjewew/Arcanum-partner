package com.mahavira.partner.profile.domain.repo;

import com.mahavira.partner.base.entity.Partner;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 23/07/18.
 *
 */

public interface ProfileRepository {
    Single<Partner> getProfileByEmail(String email);
    Completable updateProfile(Partner partner);
}
