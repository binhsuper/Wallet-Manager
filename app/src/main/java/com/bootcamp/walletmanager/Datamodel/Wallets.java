package com.bootcamp.walletmanager.Datamodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;

public class Wallets extends RealmObject {
    private String name;
    private int amount;
    private int walletType;
    private String dayCreated;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setWalletType(int walletType) {
        this.walletType = walletType;
    }

    public void setDayCreated(String dayCreated) {
        this.dayCreated = dayCreated;
    }

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
}

