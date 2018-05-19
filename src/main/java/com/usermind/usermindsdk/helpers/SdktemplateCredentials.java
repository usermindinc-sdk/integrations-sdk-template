package com.usermind.usermindsdk.helpers;

import static com.google.common.base.Preconditions.checkNotNull;

public class SdktemplateCredentials {

    private String accountName = "";
    private String token = "";

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = checkNotNull(accountName);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = checkNotNull(token);
    }
}
