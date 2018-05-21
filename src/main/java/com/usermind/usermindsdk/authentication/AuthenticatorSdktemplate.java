package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.fetch.metadata.MetadataFetchData;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorSdktemplate implements Authenticator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatorSdktemplate.class);

    private MetadataFetchSdktemplate metadataFetch;

    @Autowired
    public AuthenticatorSdktemplate(MetadataFetchSdktemplate metadataFetch) {
        this.metadataFetch = metadataFetch;
    }

    @Override
    public MetadataFetchData performAuthentication(String incomingCredentials) throws NoSuchMethodException {
        //TODO - implement
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }
//{accountName: "name", token: "key"}

}
