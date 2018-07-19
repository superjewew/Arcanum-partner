package com.mahavira.arcanumpartner;

import android.os.Bundle;

import com.mahavira.arcanumpartner.databinding.ActivityMainBinding;
import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.login.LoginRouter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    LoginRouter mRouter;

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRouter.goToLogin(this);
    }
}
