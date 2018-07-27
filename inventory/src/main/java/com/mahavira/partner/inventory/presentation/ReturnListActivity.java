package com.mahavira.partner.inventory.presentation;

import android.os.Bundle;

import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.inventory.R;
import com.mahavira.partner.inventory.databinding.ActivityReturnListBinding;

public class ReturnListActivity extends BaseActivity<ActivityReturnListBinding, ReturnListViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
