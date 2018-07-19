package com.mahavira.partnersms.base.presentation;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * The base Activity class using MVVM architecture together with DataBinding & Dagger compatibility.
 * Contains base functionality to prepare Activity with MVVM, DataBinding & Dagger to reduce
 * boilerplate code. Set T for the DataBinding class associated with the Activity's layout, and set
 * V for ViewModel associated with the Activity.
 *
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends
        AppCompatActivity implements HasSupportFragmentInjector {

    protected static final int NO_VIEW_MODEL_BINDING_VARIABLE = -1;

    private T mDataBinding;

    private V mViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    /**
     * Override to set view model binding variable
     * return {@value #NO_VIEW_MODEL_BINDING_VARIABLE} in case no need to bind ViewModel into
     * DataBinding, which mean there is no ViewModel variable declaration on Activity layout.
     *
     * @return variable id, generated on BR(Binding Resource) class, e.g : BR.viewModel
     */
    public abstract int getViewModelBindingVariable();

    /**
     * Override to set Activity layout resource id
     *
     * @return layout resource id, generated on R(Resource) class, e.g : R.layout.main_menu_activity
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * The method returns the DataBinding instance of the Activity
     *
     * @return T generic type of DataBinding
     */
    public final T getDataBinding() {
        return mDataBinding;
    }

    /**
     * The method returns ViewModel instance of the Activity
     *
     * @return V generic type of ViewModel
     */
    public final V getViewModel() {
        return mViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        if (this instanceof ExtraInjectable && getIntent().getExtras() != null) {
            ((ExtraInjectable) this).injectExtras(getIntent().getExtras());
        }

        provideViewModel();
        performDataBinding();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    private void performDataBinding() {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        if (getViewModelBindingVariable() != NO_VIEW_MODEL_BINDING_VARIABLE) {
            setViewModelBindingVariable();
        }
    }

    private void setViewModelBindingVariable() {
        mDataBinding.setVariable(getViewModelBindingVariable(), mViewModel);
        mDataBinding.executePendingBindings();
    }

    private void provideViewModel() {
        Class<V> clazz = getViewModelClass(getClass());
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(clazz);
    }

    private Class<V> getViewModelClass(Class<?> aClass) {
        Type type = aClass.getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            //noinspection unchecked
            return (Class<V>) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            return getViewModelClass(aClass.getSuperclass());
        }
    }
}