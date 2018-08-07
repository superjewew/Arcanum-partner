package com.mahavira.partner.dashboard.presentation;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahavira.partner.base.entity.Boardgame;

import java.util.List;

/**
 * Created by norman on 07/08/18.
 */

public class DashboardBindingAdapter {

    @BindingAdapter("app:borrowedList")
    public static void setBorrowedList(LinearLayout view, List<Boardgame> boardgames) {
        if(boardgames != null) {
            for(Boardgame boardgame : boardgames) {
                TextView textView = new TextView(view.getContext());
                textView.setText(boardgame.getName());
                view.addView(textView);
            }
        }
    }
}
