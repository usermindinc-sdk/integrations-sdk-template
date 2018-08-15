package com.usermind.usermindsdk.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorSdktemplate implements Authenticator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorSdktemplate.class);

    @Override
    public AuthenticationData performAuthentication(String incomingCredentials) throws NoSuchMethodException {
        LOGGER.info("Sdktemplate authentication started.");
        //TODO - implement
        throw new NoSuchMethodException("Authentication has not been implemented for this integration type.");
    }

}
