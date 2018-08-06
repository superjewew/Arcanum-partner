package com.mahavira.partner.dashboard.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.mahavira.partner.base.prefs.UserSharedPrefs;
import com.mahavira.partner.base.presentation.BaseActivity;
import com.mahavira.partner.base.presentation.ExtraInjectable;
import com.mahavira.partner.dashboard.R;
import com.mahavira.partner.dashboard.databinding.ActivityDashboardBinding;
import com.mahavira.partner.inventory.domain.entity.Partner;
import com.mahavira.partner.inventory.presentation.InventoryRouter;

import javax.inject.Inject;

import dagger.multibindings.IntoMap;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel> implements ExtraInjectable {

    public static final String EMAIL_EXTRA = "email";

    private String mPartnerEmail;

    @Inject
    InventoryRouter mInvRouter;

    @Inject
    UserSharedPrefs mPrefs;

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

        getDataBinding().returnGamesBtn.setOnClickListener(v -> mInvRouter.goToReturnList(this, mPartnerEmail));

        getViewModel().getPartnerData().observe(this, partner -> {
            if(partner != null) {
                switch (partner.status) {
                    case SUCCESS:
                        Toast.makeText(this, "Load Profile Success", Toast.LENGTH_SHORT).show();
                        mPrefs.setLoggedUserEmail(partner.data.getEmail());
                        break;
                    case ERROR:
                        Toast.makeText(this, "Load Profile Failed, " + partner.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        getViewModel().attemptGetProfile(mPartnerEmail);

        getViewModel().attemptSaveLoggedProfile(mPartnerEmail);
    }

    @Override
    public void injectExtras(@NonNull Bundle extras) {
        if(extras.containsKey(EMAIL_EXTRA)) {
            mPartnerEmail = extras.getString(EMAIL_EXTRA);
        }
    }
}
