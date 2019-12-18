package com.bootcamp.walletmanager.Application;

import android.app.Application;

import com.bootcamp.walletmanager.Datamodel.Account;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class WalletManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Account> accounts = realm.where(Account.class).findAll();
                if (accounts.size() == 0) {
                    Account account = realm.createObject(Account.class, UUID.randomUUID().toString()); // Create a new object
                    account.setName("admin");
                    account.setEmail("hoangthebinh259@gmail.com");
                    account.setPassword("123456");
                    account.setLogged(false);
                }
            }
        });
    }
}
