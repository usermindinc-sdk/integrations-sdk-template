package com.usermind.usermindsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClassFactory {

    private static final Set<String> entityList = new HashSet<>(Arrays.asList("", ""));
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Set<String> getEntitySet() {
        return entityList;
    }

    public static CredentialContainerSdktemplate getCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getWorkingTestCredentials());
    }

    public static CredentialContainerSdktemplate getInvalidCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getNonWorkingTestCredentials());
    }

    public static CredentialContainerSdktemplate getCredentialContainerSdktemplate(String inputCredentials)  {
        CredentialContainerSdktemplate credentialContainerSdktemplate = new CredentialContainerSdktemplate(objectMapper);
        try {
            credentialContainerSdktemplate.load(inputCredentials);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return credentialContainerSdktemplate;
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
