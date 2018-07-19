package com.mahavira.partnersms.base.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * The contract interface as marker for any classes (Activity, Fragment) that needs to inject
 * extras bundle into it
 *
 */

public interface ExtraInjectable {

    /**
     * Override this method to inject the values from extras bundle
     * This method is automatically called in onCreate of Activity or Fragment
     */
    void injectExtras(@NonNull Bundle extras);
}
