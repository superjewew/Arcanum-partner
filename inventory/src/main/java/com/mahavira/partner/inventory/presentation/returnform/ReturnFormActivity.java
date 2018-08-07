package com.mahavira.partner.inventory.presentation.returnform;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.entity.Partner;
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

    private Partner mPartner;

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

        getAndObservePartnerData();
        getAndObserveReturnRequestData();

        getViewModel().attemptGetProfile();
        getDataBinding().setProduct(mProduct);
    }

    private void getAndObserveReturnRequestData() {
        getViewModel().getReturnRequestData().observe(this, returnRequest -> {
            if(returnRequest != null) {
                switch (returnRequest.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Request Submitted", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(this, returnRequest.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void getAndObservePartnerData() {
        getViewModel().getPartnerData().observe(this, partnerResource -> {
            if(partnerResource != null) {
                switch (partnerResource.status) {
                    case SUCCESS:
                        mPartner = partnerResource.data;
                        getDataBinding().setPartner(partnerResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, partnerResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PRODUCT_EXTRA)) {
            mProduct = Parcels.unwrap(extras.getParcelable(PRODUCT_EXTRA));
        }
    }
}
