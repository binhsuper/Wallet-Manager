package com.bootcamp.walletmanager.Datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<Wallets> wallet;

    public List<Wallets> getWallet() {
        return wallet;
    }

    public WalletData() {
        initializeData();
    }

    public void initializeData(){
        wallet = new ArrayList<>();
        wallet.add(new Wallets("Bank account", 40000, WalletType.BANK_ACCOUNT.getValue()));
        wallet.add(new Wallets("Cash", 200000, WalletType.NORMAL.getValue()));
        wallet.add(new Wallets("Saving", 300000, WalletType.SAVINGS.getValue()));
    }

}
