package com.bootcamp.walletmanager.Application;

import android.app.Application;

import io.realm.Realm;

public class WalletManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
