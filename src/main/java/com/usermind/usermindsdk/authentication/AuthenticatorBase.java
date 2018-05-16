package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.fetch.json.events.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthenticatorBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorBase.class);

    public Events authenticate(String accountName, String apiKey) throws NoSuchMethodException {
        LOGGER.info("Authenticator called");
        return performAuthentication(accountName, apiKey);
    }

    abstract Events performAuthentication(String accountName, String apiKey) throws NoSuchMethodException;
}
