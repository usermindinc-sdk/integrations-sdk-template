package com.usermind.usermindsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.ConnectionDataSdktemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClassFactory {

    private static final Set<String> entityList = new HashSet<>(Arrays.asList("", ""));
    private static ObjectMapper objectMapper = TestBase.setupObjectMapper();

    public static Set<String> getEntitySet() {
        return entityList;
    }

    public static ConnectionDataSdktemplate getCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getWorkingTestCredentials());
    }

    public static ConnectionDataSdktemplate getInvalidCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getNonWorkingTestCredentials());
    }

    public static ConnectionDataSdktemplate getCredentialContainerSdktemplate(String inputCredentials)  {
        ConnectionDataSdktemplate credentialContainerSdktemplate = new ConnectionDataSdktemplate();
        try {
            return (ConnectionDataSdktemplate) credentialContainerSdktemplate.getInstance(inputCredentials, objectMapper);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //TODO: Put proper credentials into the next two methods. One should be valid, and one should be a valid format but not authenticate against the test system.
    //Fill in your information inside the credentials block.
    public static String getWorkingTestCredentials() {
        return "{\"credentials\":{\"appId\":\"aaa\",\"appSecret\":\"aaa\"}}";
    }

    public static String getNonWorkingTestCredentials() {
        return "{\"credentials\":{\"appId\":\"bbb\",\"appSecret\":\"bbb\"}}";
    }
}
