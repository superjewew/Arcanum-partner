package com.mahavira.partner.inventory.presentation.returnform;

import android.databinding.BindingAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by norman on 06/08/18.
 */

public class ReturnFormBindingAdapter {

    @BindingAdapter("app:components")
    public static void setComponents(LinearLayout view, List<String> components) {
        if(components != null) {
            for(String component : components) {
                CheckBox cb = new CheckBox(view.getContext());
                cb.setText(component);
                view.addView(cb);
            }
        }
    }

}
