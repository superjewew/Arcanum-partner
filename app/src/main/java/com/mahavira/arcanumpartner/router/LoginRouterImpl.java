package com.mahavira.arcanumpartner.router;

import android.content.Context;
import android.content.Intent;

import com.mahavira.partner.login.LoginRouter;
import com.mahavira.partner.login.presentation.LoginActivity;

/**
 * Created by norman on 13/07/18.
 *
 */

public class LoginRouterImpl implements LoginRouter {
    @Override
    public void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
