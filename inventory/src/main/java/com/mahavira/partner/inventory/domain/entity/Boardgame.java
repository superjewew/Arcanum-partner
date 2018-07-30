package com.mahavira.partner.inventory.domain.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by norman on 17/07/18.
 *
 */

@Parcel
public class Boardgame extends BaseObservable {
    String name;
    int quantity;
    List<String> component;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getComponent() {
        return component;
    }

    public void setComponent(List<String> component) {
        this.component = component;
    }
}
