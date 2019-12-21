package com.bootcamp.walletmanager.Datamodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;

public class Wallets extends RealmObject {
    
    public enum WalletType {
        NORMAL(1),
        BANK_ACCOUNT(2),
        CREDIT_CARD(3),
        SAVINGS(4);

        private int value;
        private static Map map = new HashMap<>();

        private WalletType(int value) {
            this.value = value;
        }

        static {
            for (WalletType walletType : WalletType.values()) {
                map.put(walletType.value, walletType);
            }
        }

        public static WalletType valueOf(int pageType) {
            return (WalletType) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    private String name;
    private int amount;
    private int walletType;
    private Date dayCreated;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setWalletType(int walletType) {
        this.walletType = walletType;
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

    public Date getDayCreated() {
        return dayCreated;
    }

    public void setDayCreated(Date dayCreated) {
        this.dayCreated = dayCreated;
    }
}

