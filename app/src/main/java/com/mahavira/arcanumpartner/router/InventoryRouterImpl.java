package com.mahavira.arcanumpartner.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partner.inventory.presentation.InventoryRouter;
import com.mahavira.partner.inventory.presentation.ReturnListActivity;

/**
 * Created by norman on 27/07/18.
 *
 */

public class InventoryRouterImpl implements InventoryRouter {

    @Override
    public void goToReturnList(Context context, String email) {
        Intent intent = new Intent(context, ReturnListActivity.class);
        intent.putExtra(ReturnListActivity.PARTNER_EMAIL_EXTRA, email);
        context.startActivity(intent);
    }
}
