package com.usermind.usermindsdk.fetch.samplefetch;


import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.fetchsetup.MetadataFetchSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SampleFetchSdktemplate implements SampleFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleFetchSdktemplate.class);

    private final MetadataFetchSdktemplate metadataFetchSdktemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public SampleFetchSdktemplate(RestTemplate restTemplate, MetadataFetchSdktemplate metadataFetchSdktemplate) {
        this.restTemplate = restTemplate;
        this.metadataFetchSdktemplate = metadataFetchSdktemplate;
    }

    @Override
    public FetchedData performSampleFetch(String incomingCredentials) throws NoSuchMethodException {
        LOGGER.info("Running sample fetch");
        //TODO - implement
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }




}
