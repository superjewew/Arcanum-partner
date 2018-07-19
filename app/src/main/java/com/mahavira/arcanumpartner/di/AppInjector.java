package com.mahavira.arcanumpartner.di;

import com.mahavira.arcanumpartner.BaseApplication;

public class AppInjector {

    private AppInjector() {
    }

    public static void init(BaseApplication application) {
        DaggerAppComponent
                .builder()
                .application(application)
                .build()
                .inject(application);

    }
}