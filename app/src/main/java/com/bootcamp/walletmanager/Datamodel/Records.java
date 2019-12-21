package com.bootcamp.walletmanager.Datamodel;

import android.widget.ImageView;

import java.util.Date;

import io.realm.RealmObject;

public class Records extends RealmObject {
    String fromWallet;
    String type;
    Date date;
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

    public String getAmount() {
        return amount;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
