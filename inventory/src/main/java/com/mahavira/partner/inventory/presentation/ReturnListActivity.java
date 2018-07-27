package com.mahavira.partner.inventory.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.base.presentation.ExtraInjectable;
import com.mahavira.partner.inventory.BR;
import com.mahavira.partner.inventory.R;
import com.mahavira.partner.inventory.databinding.ActivityReturnListBinding;

public class ReturnListActivity extends BaseActivity<ActivityReturnListBinding, ReturnListViewModel> implements ExtraInjectable{

    public static final String PARTNER_EMAIL_EXTRA = "email";

    private ReturnListAdapter mAdapter;

    private String mPartnerEmail;

    @Override
    public int getViewModelBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupAdapter();
        setupRecyclerView(mAdapter);

        getViewModel().getBorrowedGamesData().observe(this, products -> {
            if(products != null) {
                switch (products.status) {
                    case SUCCESS:
                        mAdapter.replaceProducts(products.data);
                        break;
                    case ERROR:
                        Toast.makeText(this, "Error retrieving data, " + products.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptGetBorrowed(mPartnerEmail);
    }

    private void setupRecyclerView(ReturnListAdapter adapter) {
        getDataBinding().borrowedGamesList.setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().borrowedGamesList.setAdapter(adapter);
    }

    private void setupAdapter() {
        mAdapter = new ReturnListAdapter(this);
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(PARTNER_EMAIL_EXTRA)) {
            mPartnerEmail = extras.getString(PARTNER_EMAIL_EXTRA);
        }
    }
}
