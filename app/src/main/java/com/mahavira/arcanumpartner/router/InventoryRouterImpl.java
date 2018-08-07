package com.mahavira.arcanumpartner.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.inventory.presentation.InventoryRouter;
import com.mahavira.partner.inventory.presentation.returnform.ReturnFormActivity;
import com.mahavira.partner.inventory.presentation.returnlist.ReturnListActivity;

import org.parceler.Parcels;

import static com.mahavira.partner.inventory.presentation.returnform.ReturnFormActivity.PRODUCT_EXTRA;

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

    @Override
    public void goToReturnForm(Context context, String product) {
        Intent intent = new Intent(context, ReturnFormActivity.class);
        intent.putExtra(PRODUCT_EXTRA, product);
        context.startActivity(intent);
    }
}
