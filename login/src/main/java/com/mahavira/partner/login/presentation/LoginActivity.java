package com.mahavira.partner.login.presentation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mahavira.partner.dashboard.presentation.DashboardRouter;
import com.mahavira.partner.login.BR;
import com.mahavira.partner.login.R;

import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.login.databinding.ActivityLoginBinding;
import com.mahavira.partner.login.domain.entity.AuthParam;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Inject
    DashboardRouter mDashboardRouter;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getLoginResponse().observe(this, authResultResource -> {
            if (authResultResource != null) {
                switch (authResultResource.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                        mDashboardRouter.goToDashboard(this, authResultResource.data.getUser().getEmail());
                        break;
                    case ERROR:
                        Toast.makeText(this, "Login Failed, " + authResultResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getDataBinding().setParam(new AuthParam("", ""));
    }
}
