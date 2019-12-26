package com.bootcamp.walletmanager.Application;

import android.app.Application;

import com.bootcamp.walletmanager.Datamodel.Account;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class WalletManagerApplication extends Application {

    Realm realm;
    public static RealmConfiguration config;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        config = new RealmConfiguration.Builder()
                .name("walletmanager.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Account> accounts = realm.where(Account.class).findAll();
                if (accounts.size() == 0) {
                    Account account = realm.createObject(Account.class, UUID.randomUUID().toString()); // Create a new object
                    account.setName("admin");
                    account.setEmail("admin@gmail.com");
                    account.setPassword("123456");
                    account.setLogged(false);
                }
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        realm.close();
    }
}
