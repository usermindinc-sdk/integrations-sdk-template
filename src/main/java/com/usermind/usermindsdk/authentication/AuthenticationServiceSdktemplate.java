package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServiceSdktemplate implements AuthenticationService<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    //Use this variable to fill in the URL of the path to authenticate - then the unit tests will work. If you change
    //this, just change the tests to match.
    public static final String AUTH_CHECKING_PATH = "https://graph.facebook.com/v3.1/me/permissions";

    @Autowired
    public AuthenticationServiceSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public AuthenticatorResponse validate(CredentialContainerSdktemplate credentials) throws NoSuchMethodException {
        LOGGER.info("Sdktemplate authentication started.");
        //TODO - implement Authenticator
        //If there is a session - this call is all you need here to validate. But ideally you'll then
        //get an entity list.
        //SessionCredentialContainerSdktemplate sessionInformation = sessionCredentials.validate(credentials);

        //If there is no session - simply take the credentials and call something, and make sure
        //the call works and validates.
        throw new NoSuchMethodException("Authentication has not been implemented for this integration type.");
    }

}