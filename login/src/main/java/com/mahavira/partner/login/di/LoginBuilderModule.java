package com.mahavira.partner.login.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partner.login.presentation.LoginActivity;
import com.mahavira.partner.login.presentation.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import com.mahavira.partner.base.di.ViewModelKey;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module
public abstract class LoginBuilderModule {

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
}
