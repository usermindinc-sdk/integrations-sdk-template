package com.usermind.usermindsdk.fetch.samplefetch;


import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public abstract class SampleFetchBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleFetchBase.class);

    protected final RunPoller runPoller;
    protected final MetadataFetch metadataFetch;
    protected RestTemplate restTemplate;

    @Autowired
    public SampleFetchBase(RestTemplate restTemplate, RunPoller runPoller, MetadataFetch metadataFetch) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.metadataFetch = metadataFetch;
    }

    public FetchedData runSampleFetch() throws NoSuchMethodException {
        //This is here so that we can catch the error in a base class and
        //transform it to a desired return error
        //TODO - catch and transform errors
        LOGGER.info("Running sample fetch");
        return performSampleFetch();
     }

    abstract protected FetchedData performSampleFetch() throws NoSuchMethodException;



}
