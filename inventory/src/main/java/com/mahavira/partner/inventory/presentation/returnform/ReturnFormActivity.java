package com.mahavira.partner.inventory.presentation.returnform;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.entity.Partner;
import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.base.presentation.ExtraInjectable;
import com.mahavira.partner.inventory.BR;
import com.mahavira.partner.inventory.R;
import com.mahavira.partner.inventory.databinding.ActivityReturnFormBinding;
import com.mahavira.partner.inventory.domain.entity.ReturnRequest;

import java.util.HashMap;
import java.util.Map;

public class ReturnFormActivity extends BaseActivity<ActivityReturnFormBinding, ReturnFormViewModel>
        implements ExtraInjectable {

    public static final String PRODUCT_EXTRA = "product";

    private String mProductName;

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
        getAndObserveBoardgameData();

        getViewModel().attemptGetProfile();
        getViewModel().attemptGetBoardgameData(mProductName);

        getDataBinding().returnBtn.setOnClickListener(v -> {
            ReturnRequest req = createReturnRequest(mProduct, mPartner);
            getViewModel().attemptReturnGames(req);
        });
    }

    private ReturnRequest createReturnRequest(Boardgame product, Partner partner) {
        ReturnRequest request = new ReturnRequest();
        request.setProductName(product.getName());
        request.setFrom(partner.getName());
        request.setChecklist(createCheckList());
        return request;
    }

    private Map<String, Boolean> createCheckList() {
        Map<String, Boolean> checklist = new HashMap<>();

        LinearLayout layout = getDataBinding().checklistLayout;
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layout.getChildAt(i);
            if(v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                checklist.put(cb.getText().toString(), cb.isChecked());
            }
        }

        return checklist;
    }

    private void getAndObserveBoardgameData() {
        getViewModel().getBoardgameData().observe(this, boardgameResource -> {
            if(boardgameResource != null) {
                switch (boardgameResource.status) {
                    case SUCCESS:
                        mProduct = boardgameResource.data;
                        getDataBinding().setProduct(boardgameResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, boardgameResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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
            mProductName = extras.getString(PRODUCT_EXTRA);
        }
    }
}
