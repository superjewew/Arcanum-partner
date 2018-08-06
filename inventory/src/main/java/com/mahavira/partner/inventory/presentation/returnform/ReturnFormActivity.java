package com.mahavira.partner.inventory.presentation.returnform;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.base.presentation.ExtraInjectable;
import com.mahavira.partner.inventory.BR;
import com.mahavira.partner.inventory.R;
import com.mahavira.partner.inventory.databinding.ActivityReturnFormBinding;

import org.parceler.Parcels;

public class ReturnFormActivity extends BaseActivity<ActivityReturnFormBinding, ReturnFormViewModel>
        implements ExtraInjectable {

    public static final String PRODUCT_EXTRA = "product";

    private Boardgame mProduct;

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

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PRODUCT_EXTRA)) {
            mProduct = Parcels.unwrap(extras.getParcelable(PRODUCT_EXTRA));
        }
    }
}
