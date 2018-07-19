package com.mahavira.partnersms.base.core;

import io.reactivex.Single;

/**
 * Created by norman on 29/01/18.
 */

public interface BaseUseCase<R> {
    Single<R> execute();
}
