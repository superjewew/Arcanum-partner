package com.mahavira.arcanumpartner.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partner.dashboard.presentation.DashboardActivity;
import com.mahavira.partner.dashboard.presentation.DashboardRouter;

/**
 * Created by norman on 23/07/18.
 *
 */

public class DashboardRouterImpl implements DashboardRouter {
    @Override
    public void goToDashboard(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }
}
