package com.mahavira.partner.base.core;

import io.reactivex.Completable;

public interface CompletableUseCase<P> {
    Completable execute(P param) throws Exception;
}
