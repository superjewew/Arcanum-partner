package com.mahavira.partner.inventory.di;

import android.arch.lifecycle.ViewModel;

import com.mahavira.partner.base.di.ViewModelKey;
import com.mahavira.partner.inventory.presentation.returnform.ReturnFormActivity;
import com.mahavira.partner.inventory.presentation.returnform.ReturnFormViewModel;
import com.mahavira.partner.inventory.presentation.returnlist.ReturnListActivity;
import com.mahavira.partner.inventory.presentation.returnlist.ReturnListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by norman on 27/07/18.
 */

@Module
public abstract class InventoryBuilderModule {

    @ContributesAndroidInjector
    abstract ReturnListActivity bindReturnListActivity();

    @ContributesAndroidInjector
    abstract ReturnFormActivity bindReturnFormActivity();

    @Binds
    @IntoMap
    @ViewModelKey(ReturnListViewModel.class)
    abstract ViewModel bindReturnListViewModel(ReturnListViewModel returnListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReturnFormViewModel.class)
    abstract ViewModel bindReturnFormViewModel(ReturnFormViewModel returnFormViewModel);
}
