package com.mahavira.partner.inventory.di;

import com.mahavira.partner.base.di.ViewModelKey;
import com.mahavira.partner.inventory.presentation.ReturnListActivity;
import com.mahavira.partner.inventory.presentation.ReturnListViewModel;

import dagger.Binds;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 27/07/18.
 */

public abstract class InventoryBuilderModule {

    @ContributesAndroidInjector
    abstract ReturnListActivity bindReturnListActivity();

    @Binds
    @IntoMap
    @ViewModelKey(ReturnListViewModel.class)
    abstract ReturnListViewModel bindReturnListViewModel(ReturnListViewModel returnListViewModel);
}
