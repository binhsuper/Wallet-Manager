package com.bootcamp.walletmanager.Datamodel;

import java.util.ArrayList;
import java.util.List;

public class Wallets {
    String name;
    String amount;
    int value;

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public int getValue() {
        return value;
    }

    public Wallets(String name, String amount, int value) {
        this.name = name;
        this.amount = amount;
        this.value = value;
    }
}

