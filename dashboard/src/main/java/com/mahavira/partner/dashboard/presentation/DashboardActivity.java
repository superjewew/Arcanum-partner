package com.mahavira.partner.dashboard.presentation;

import android.os.Bundle;

import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.dashboard.R;
import com.mahavira.partner.dashboard.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}
