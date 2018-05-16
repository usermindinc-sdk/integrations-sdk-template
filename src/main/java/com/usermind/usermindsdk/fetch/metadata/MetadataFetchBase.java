package com.usermind.usermindsdk.fetch.metadata;

import com.usermind.usermindsdk.fetch.json.events.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public abstract class MetadataFetchBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetchBase.class);

    protected RestTemplate restTemplate;

    public MetadataFetchBase(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MetadataFetchData runMetadataFetch(String accountName, String apiKey) throws NoSuchMethodException {
        //This is here so that we can catch the error in a base class and
        //transform it to a desired return error
        //TODO - catch and transform errors
        LOGGER.info("Running metadata fetch");
        return performMetadataFetch(accountName, apiKey);
    }

    abstract MetadataFetchData performMetadataFetch(String accountName, String apiKey) throws NoSuchMethodException;


}
