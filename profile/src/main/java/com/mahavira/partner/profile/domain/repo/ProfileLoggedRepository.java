package com.mahavira.partner.profile.domain.repo;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by norman on 06/08/18.
 */

public interface ProfileLoggedRepository {
    Completable setLoggedProfileEmail(String email);
    Single<String> getLoggedProfileEmail();
}
