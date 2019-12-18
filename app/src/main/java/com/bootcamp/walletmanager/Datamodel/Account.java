package com.bootcamp.walletmanager.Datamodel;

public class Account {
    String name;
    String email;
    String password;
    int logged;

    public Account() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogged(int logged) {
        this.logged = logged;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLogged() {
        return logged;
    }
}
