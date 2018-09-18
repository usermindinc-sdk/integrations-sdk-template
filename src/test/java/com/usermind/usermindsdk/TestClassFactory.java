package com.usermind.usermindsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.CredentialContainerSdktemplate;

import java.io.IOException;

public class TestClassFactory {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static CredentialContainerSdktemplate getCredentialContainerSdktemplate() {
        CredentialContainerSdktemplate credentialContainerSdktemplate = new CredentialContainerSdktemplate(objectMapper);
        try {
            //this is done simply to let me remove the throws statement from the method call, which caused problem
            //when streaming. In testing I was comfortable with that.
            credentialContainerSdktemplate.load(getWorkingTestCredentials());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return credentialContainerSdktemplate;
    }

    public static String getWorkingTestCredentials() {
        return "{\"credentials\":{\"apiId\":\"tz37h4mboh60\",\"apiSecret\":\"U4LlQn8bjOXUU8BW7qcOBtgHldwQX2DQh2waVgKvllMrZ2NoR21brgRATtKlD3npeEd5It+dE/yiq4+jSiAxow==\",\"domain\":\"schneider-electric3-pilot.csod.com\",\"username\":\"ADM_Usermind\"}}";
    }

}
