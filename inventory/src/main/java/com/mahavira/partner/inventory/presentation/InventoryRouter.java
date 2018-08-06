package com.mahavira.partner.inventory.presentation;

import android.content.Context;

import com.mahavira.partner.base.entity.Boardgame;

/**
 * Created by norman on 27/07/18.
 *
 */

public interface InventoryRouter {
    void goToReturnList(Context context, String email);
    void goToReturnForm(Context context, Boardgame product);
}
