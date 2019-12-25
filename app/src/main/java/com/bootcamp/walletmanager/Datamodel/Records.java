package com.bootcamp.walletmanager.Datamodel;

import android.widget.ImageView;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Records extends RealmObject {
    @PrimaryKey
    private String recordID;

    private String fromWallet;
    private String type;
    private Date date;
    private String amount;
    private String notes;
    private String kind;

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

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
