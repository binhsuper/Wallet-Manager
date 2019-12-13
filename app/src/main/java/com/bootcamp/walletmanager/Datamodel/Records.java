package com.bootcamp.walletmanager.Datamodel;

import android.widget.ImageView;

public class Records {
    String name;
    String fromWallet;
    String date;
    String amount;
    int typeImg;

    public String getName() {
        return name;
    }

    public String getFromWallet() {
        return fromWallet;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public int getTypeImg() {
        return typeImg;
    }

    public Records(String name, String fromWallet, String date, String amount, int typeImg) {
        this.name = name;
        this.fromWallet = fromWallet;
        this.date = date;
        this.amount = amount;
        this.typeImg = typeImg;
    }
}
