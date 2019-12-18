package com.bootcamp.walletmanager.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaSync;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bootcamp.walletmanager.Datamodel.Account;
import com.bootcamp.walletmanager.Datamodel.Wallets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class CustomActivity extends AppCompatActivity {

    public String TAG = "authentication";
    public Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        realm = Realm.getDefaultInstance();
    }


    // TODO: User account data persistence functions.

    public void createNewAccount(final String name, final String email, final String password) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Account account = realm.createObject(Account.class, UUID.randomUUID().toString()); // Create a new object
                account.setName(name);
                account.setEmail(email);
                account.setPassword(password);
                account.setLogged(true);
            }
        });
    }

    public void getCreatedAccounts() {
        RealmResults<Account> accounts = realm.where(Account.class).findAll();
        for (int i = 0; i < accounts.size(); i++) {
            Log.d(TAG, "name: " + accounts.get(i).getName());
            Log.d(TAG, "email: " + accounts.get(i).getEmail());
            Log.d(TAG, "logged: " + accounts.get(i).isLogged());
            Log.d(TAG, "password: " + accounts.get(i).getPassword());
            Log.d(TAG, " ");
        }
    }

    public Account getLoggedAccount(String email) {
        Account account = new Account();
        RealmResults<Account> accounts = realm.where(Account.class).findAll();
        for (int i = 0; i < accounts.size(); i++) {
            if (email.equals(accounts.get(i).getEmail())) {
                account = accounts.get(i);
                realm.beginTransaction();
                account.setLogged(true);
                realm.commitTransaction();
            }
        }
        return account;
    }

    public Boolean checkLogin(String email, String password) {
        Boolean currentLogin = false;

        RealmResults<Account> accounts = realm.where(Account.class).findAll();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                currentLogin = true;
            }
        }
        return currentLogin;
    }


    // TODO: Create wallet data functions.

    public void createNewWallet(final String name, final int amount, final int walletType, final String dayCreated) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Wallets wallet = realm.createObject(Wallets.class); // Create a new object
                wallet.setName(name);
                wallet.setAmount(amount);
                wallet.setWalletType(walletType);
                wallet.setDayCreated(dayCreated);
            }
        });
    }

    public void getCreatedWallets() {
        RealmResults<Wallets> wallets = realm.where(Wallets.class).findAll();
        for (int i = 0; i < wallets.size(); i++) {
            Log.d(TAG, "name: " + wallets.get(i).getName());
            Log.d(TAG, "amount: " + wallets.get(i).getAmount());
            Log.d(TAG, "type: " + wallets.get(i).getWalletType());
            Log.d(TAG, "dayCreated: " + wallets.get(i).getDayCreated());
            Log.d(TAG, " ");
        }
    }



    // TODO: Create deal data functions.

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
