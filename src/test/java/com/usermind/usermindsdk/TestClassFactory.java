package com.usermind.usermindsdk;

import com.usermind.usermindsdk.helpers.TitoCredentials;

public class TestClassFactory {

    public static TitoCredentials getTitoCredentials() {
        TitoCredentials credentials = new TitoCredentials();
        credentials.setAccountName("ragi-test");
        credentials.setToken("nM_bPyV4sfbVBz8Po28g");
        return credentials;
    }

    public static String getTitoCredentialString() {
        return "{\"accountName\": \"ragi-test\", \"token\": \"nM_bPyV4sfbVBz8Po28g\"}";
    }
}
