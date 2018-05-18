package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.fetch.metadata.MetadataFetchData;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchTito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorTito implements Authenticator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorTito.class);

    private MetadataFetchTito metadataFetch;

    @Autowired
    public AuthenticatorTito(MetadataFetchTito metadataFetch) {
        this.metadataFetch = metadataFetch;
    }

    @Override
    public MetadataFetchData performAuthentication(String incomingCredentials) throws NoSuchMethodException {
        LOGGER.info("Authenticator called");
        return metadataFetch.performMetadataFetch(incomingCredentials);
    }
//{accountName: "name", token: "key"}
//objectMapper.readValue(connectionDataStr, Input.class);

}
