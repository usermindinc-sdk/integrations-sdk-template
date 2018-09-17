package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticatorServiceSdktemplate implements AuthenticationService<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorServiceSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticatorServiceSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public AuthenticatorResponse validate(CredentialContainerSdktemplate credentials) throws NoSuchMethodException {
        LOGGER.info("Sdktemplate authentication started.");
        //TODO - implement Authenticator
        throw new NoSuchMethodException("Authentication has not been implemented for this integration type.");
    }

}
