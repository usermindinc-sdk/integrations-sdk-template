package com.usermind.usermindsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.helpers.CredentialContainerSdktemplate;

import java.io.IOException;

public class TestClassFactory {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static CredentialContainerSdktemplate getCredentialContainerSdktemplate() throws IOException {
        CredentialContainerSdktemplate credentialContainerSdktemplate = new CredentialContainerSdktemplate(objectMapper);
        credentialContainerSdktemplate.load(getWorkingTestCredentials());
        return credentialContainerSdktemplate;
    }

    public static String getWorkingTestCredentials() {
        return "{\"credentials\":{\"apiId\":\"tz37h4mboh60\",\"apiSecret\":\"U4LlQn8bjOXUU8BW7qcOBtgHldwQX2DQh2waVgKvllMrZ2NoR21brgRATtKlD3npeEd5It+dE/yiq4+jSiAxow==\",\"domain\":\"schneider-electric3-pilot.csod.com\",\"username\":\"ADM_Usermind\"}}";
    }

}
