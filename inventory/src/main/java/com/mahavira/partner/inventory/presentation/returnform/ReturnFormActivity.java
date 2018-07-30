package com.mahavira.partner.inventory.presentation.returnform;

import android.os.Bundle;

import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.inventory.BR;
import com.mahavira.partner.inventory.R;
import com.mahavira.partner.inventory.databinding.ActivityReturnFormBinding;

public class ReturnFormActivity extends BaseActivity<ActivityReturnFormBinding, ReturnFormViewModel> {

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_form;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_form);
    }
}
