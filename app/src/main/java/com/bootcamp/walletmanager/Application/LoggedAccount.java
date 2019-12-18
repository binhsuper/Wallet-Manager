package com.bootcamp.walletmanager.Application;

import com.bootcamp.walletmanager.Datamodel.Account;

public class LoggedAccount {
    private static Account currentLogin = new Account();

    public static void setCurrentLogin(Account currentLogin) {
        LoggedAccount.currentLogin = currentLogin;
    }

    public static Account getCurrentLogin() {
        return currentLogin;
    }
}
