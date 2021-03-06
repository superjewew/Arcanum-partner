package com.mahavira.arcanumpartner.di;

import android.app.Application;

import com.mahavira.arcanumpartner.BaseApplication;
import com.mahavira.partner.base.di.BaseModule;
import com.mahavira.partner.dashboard.di.DashboardModule;
import com.mahavira.partner.inventory.di.InventoryModule;
import com.mahavira.partner.login.di.LoginModule;
import com.mahavira.partner.profile.di.ProfileModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        BaseModule.class,
        LoginModule.class,
        DashboardModule.class,
        ProfileModule.class,
        InventoryModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseApplication application);
}
