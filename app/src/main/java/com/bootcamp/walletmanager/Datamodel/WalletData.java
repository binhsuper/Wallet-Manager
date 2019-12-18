package com.bootcamp.walletmanager.Datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

public class WalletData {
    public enum WalletType {
        NORMAL(1),
        BANK_ACCOUNT(2),
        CREDIT_CARD(3),
        SAVINGS(4);

        private int value;
        private static Map map = new HashMap<>();

        private WalletType(int value) {
            this.value = value;
        }

        static {
            for (WalletType walletType : WalletType.values()) {
                map.put(walletType.value, walletType);
            }
        }

        public static WalletType valueOf(int pageType) {
            return (WalletType) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    private List<Wallets> walletArray;

    public List<Wallets> getWalletArray() {
        return walletArray;
    }

    public WalletData() {
        initializeData();
    }

    public void initializeData(){
        walletArray = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Wallets> wallets = realm.where(Wallets.class).findAll();
        for (int i = 0; i < wallets.size(); i++) {
            Wallets wallet = wallets.get(i);
            walletArray.add(wallet);
        }
    }

}
