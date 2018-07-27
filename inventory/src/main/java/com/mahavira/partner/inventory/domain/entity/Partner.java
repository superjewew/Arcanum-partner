package com.mahavira.partner.inventory.domain.entity;


import com.mahavira.partner.inventory.presentation.ProductListParcelConverter;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 15/07/18.
 *
 */

@Parcel
public class Partner {
    String name;
    String username;
    String password;
    String email;
    String address;
    String phone;
    int visitorToday;
    int visitorThisMonth;
    String lastUpdated;

    @ParcelPropertyConverter(ProductListParcelConverter.class)
    List<Boardgame> borrowedGames = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVisitorToday() {
        return visitorToday;
    }

    public void setVisitorToday(int visitorToday) {
        this.visitorToday = visitorToday;
    }

    public int getVisitorThisMonth() {
        return visitorThisMonth;
    }

    public void setVisitorThisMonth(int visitorThisMonth) {
        this.visitorThisMonth = visitorThisMonth;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Boardgame> getBorrowedGames() {
        return borrowedGames;
    }

    public void setBorrowedGames(List<Boardgame> borrowedGames) {
        this.borrowedGames = borrowedGames;
    }
}
