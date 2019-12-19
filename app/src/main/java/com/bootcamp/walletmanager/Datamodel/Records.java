package com.bootcamp.walletmanager.Datamodel;

import android.widget.ImageView;

import io.realm.RealmObject;

public class Records extends RealmObject {
    String fromWallet;
    String type;
    String date;
    String amount;
    String notes;
    String kind;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setFromWallet(String fromWallet) {
        this.fromWallet = fromWallet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFromWallet() {
        return fromWallet;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }
}
