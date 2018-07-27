package com.mahavira.arcanumpartner.di;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanumpartner.router.DashboardRouterImpl;
import com.mahavira.arcanumpartner.router.InventoryRouterImpl;
import com.mahavira.arcanumpartner.router.LoginRouterImpl;
import com.mahavira.partner.dashboard.presentation.DashboardRouter;
import com.mahavira.partner.inventory.presentation.InventoryRouter;
import com.mahavira.partner.login.LoginRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 09/07/18.
 *
 */

@Module
class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    LoginRouter provideLoginRouter() {
        return new LoginRouterImpl();
    }

    @Singleton
    @Provides
    FirebaseFirestore provideFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Provides
    DashboardRouter provideDashboardRouter() {
        return new DashboardRouterImpl();
    }

    @Provides
    InventoryRouter provideInventoryRouter() {
        return new InventoryRouterImpl();
    }
}
