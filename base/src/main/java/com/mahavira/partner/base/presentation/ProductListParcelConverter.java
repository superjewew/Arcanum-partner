package com.mahavira.partner.base.presentation;

import android.os.Parcel;


import com.mahavira.partner.base.entity.Boardgame;

import org.parceler.Parcels;
import org.parceler.converter.ArrayListParcelConverter;

public class ProductListParcelConverter extends ArrayListParcelConverter<Boardgame> {

    @Override
    public void itemToParcel(Boardgame item, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(item), 0);
    }

    @Override
    public Boardgame itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Boardgame.class.getClassLoader()));
    }
}