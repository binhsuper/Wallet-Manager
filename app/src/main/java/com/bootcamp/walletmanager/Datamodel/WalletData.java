package com.bootcamp.walletmanager.Datamodel;

import java.util.ArrayList;
import java.util.List;

public class WalletData {
    private List<Wallets> wallet;

    public List<Wallets> getWallet() {
        return wallet;
    }

    public WalletData() {
        initializeData();
    }

    public void initializeData(){
        wallet = new ArrayList<>();
        wallet.add(new Wallets("Bank account", "$40000.00", 40000));
        wallet.add(new Wallets("Cash", "$200000.00", 200000));
        wallet.add(new Wallets("Saving", "$300000.00", 300000));
    }

}
