package com.bootcamp.walletmanager;

import android.widget.ImageView;

public class Records {
    String name;
    String fromWallet;
    String date;
    String amount;
    int typeImg;

    public Records(String name, String fromWallet, String date, String amount, int typeImg) {
        this.name = name;
        this.fromWallet = fromWallet;
        this.date = date;
        this.amount = amount;
        this.typeImg = typeImg;
    }
}
