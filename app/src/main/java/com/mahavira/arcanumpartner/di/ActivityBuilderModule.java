package com.mahavira.arcanumpartner.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.arcanumpartner.MainActivity;
import com.mahavira.arcanumpartner.MainViewModel;
import com.mahavira.partner.base.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 09/07/18.
 *
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel provideViewModel(MainViewModel viewModel);
}
