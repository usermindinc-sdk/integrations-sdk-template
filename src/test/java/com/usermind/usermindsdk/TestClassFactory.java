package com.usermind.usermindsdk;

import com.usermind.usermindsdk.helpers.SdktemplateCredentials;

public class TestClassFactory {

    public static SdktemplateCredentials getSdktemplateCredentials() {
        SdktemplateCredentials credentials = new SdktemplateCredentials();
        credentials.setAccountName("ragi-test");
        credentials.setToken("nM_bPyV4sfbVBz8Po28g");
        return credentials;
    }

    //Some sample code left in place ...

//    public static String getSdktemplateCredentialString() {
//        return "{\"accountName\": \"ragi-test\", \"token\": \"nM_bPyV4sfbVBz8Po28g\"}";
//    }
//
//    public static Events getEvents() throws IOException {
//        TestClassFactoryFileReader fileReader = new TestClassFactoryFileReader();
//        return fileReader.getEvents();
//    }
//
//    public static String getEventString() throws IOException {
//        TestClassFactoryFileReader fileReader = new TestClassFactoryFileReader();
//        return fileReader.getEventString();
//    }

}
