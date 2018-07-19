package com.mahavira.partner.login.domain.entity;

/**
 * Created by norman on 13/07/18.
 *
 */

public class AuthParam {
    private String mEmail;
    private String mPassword;

    public AuthParam(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
