package com.mahavira.partnersms.base.exception;

import android.support.annotation.NonNull;

public class RxFirebaseNullDataException extends NullPointerException {

    public RxFirebaseNullDataException() {
    }

    public RxFirebaseNullDataException(@NonNull String detailMessage) {
        super(detailMessage);
    }

}