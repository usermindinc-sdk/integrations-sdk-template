package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.fetch.fetchsetup.MetadataFetchSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FullFetchSdktemplate implements FullFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetchSdktemplate.class);

    private final RestTemplate restTemplate;
    private final MetadataFetchSdktemplate metadataFetchSdktemplate;

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_STRING = "Token token=";

    @Autowired
    public FullFetchSdktemplate(RestTemplate restTemplate, MetadataFetchSdktemplate metadataFetchSdktemplate) {
        this.restTemplate = restTemplate;
        this.metadataFetchSdktemplate = metadataFetchSdktemplate;
    }

    @Override
    public void performFullFetch(String incomingCredentials) throws NoSuchMethodException {
        LOGGER.info("About to perform a full fetch.");
        //TODO - implement
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");

    }

}
