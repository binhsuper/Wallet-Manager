package com.bootcamp.walletmanager.Datamodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Wallets {
    String name;
    int amount;
    int walletType;
    String dayCreated = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());


    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getWalletType() {
        return walletType;
    }

    public String getDayCreated() {
        return dayCreated;
    }

    public Wallets(String name, int amount, int walletType) {
        this.name = name;
        this.amount = amount;
        this.walletType = walletType;
    }
}

