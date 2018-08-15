package com.usermind.usermindsdk.fetch.fetchsetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MetadataFetchSdktemplate implements MetadataFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetchSdktemplate.class);

    private final RestTemplate restTemplate;

    @Autowired
    public MetadataFetchSdktemplate(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
    }

    @Override
    public MetadataFetchData performMetadataFetch(String incomingCredentials) throws NoSuchMethodException {
         LOGGER.info("Running fetchsetup fetch");
        //TODO - implement
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }



}
