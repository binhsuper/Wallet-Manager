package com.bootcamp.walletmanager.Datamodel;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Account extends RealmObject {
    @PrimaryKey
    private String ID;

    private String email;
    private String name;
    private String password;
    private boolean logged;

    private RealmList<Wallets> userWallets;
    private RealmList<Records> userRecords;

    public RealmList<Wallets> getUserWallets() {
        return userWallets;
    }

    public RealmList<Records> getUserRecords() {
        return userRecords;
    }

    public String getID() {
        return ID;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }
}
