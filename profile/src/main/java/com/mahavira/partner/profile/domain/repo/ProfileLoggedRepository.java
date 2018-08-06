package com.mahavira.partner.profile.domain.repo;

import com.mahavira.partner.profile.domain.entity.Partner;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 06/08/18.
 */

public interface ProfileLoggedRepository {
    Completable setLoggedProfileEmail(Partner partner);
    Single<String> getLoggedProfileEmail();
}
