package com.usermind.usermindsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;

public class TestClassFactory {

    private static ObjectMapper objectMapper = TestBase.setupObjectMapper();

    public static SdktemplateConnectionData getCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getWorkingTestCredentials());
    }

    public static SdktemplateConnectionData getInvalidCredentialContainerSdktemplate()  {
        return getCredentialContainerSdktemplate(getNonWorkingTestCredentials());
    }

    public static SdktemplateConnectionData getCredentialContainerSdktemplate(String inputCredentials)  {
        SdktemplateConnectionData sdktemplateConnectionData = new SdktemplateConnectionData();
        try {
            return (SdktemplateConnectionData) sdktemplateConnectionData.getInstance(inputCredentials, objectMapper);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //TODO: Put proper credentials into the next two methods. One should be valid, and one should be a valid format but not authenticate against the test system.
    //Fill in your information inside the credentials block.
    public static String getWorkingTestCredentials() {
        return "{\n" +
                "  \"encrypted\": {\n" +
                "    \"credentials\": {\n" +
                "      \"username\": \"um\",\n" +
                "      \"password\": \"pwd\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"entities\": [],\n" +
                "  \"misc\": {},\n" +
                "  \"version\": \"2.0\"\n" +
                "}";
    }

    public static String getNonWorkingTestCredentials() {
        return "{\n" +
                "  \"encrypted\": {\n" +
                "    \"credentials\": {\n" +
                "      \"username\": \"invalid_username\",\n" +
                "      \"password\": \"invalid_password\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"entities\": [],\n" +
                "  \"misc\": {},\n" +
                "  \"version\": \"2.0\"\n" +
                "}";
    }
}
