package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class Authenticator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Authenticator.class);

    private MetadataFetch metadataFetch;

    @Autowired
    public Authenticator(MetadataFetch metadataFetch) {
        this.metadataFetch = metadataFetch;
    }

    public Events authenticate(String accountName, String apiKey) {
        return metadataFetch.runMetadataFetch(accountName, apiKey);
    }

}
