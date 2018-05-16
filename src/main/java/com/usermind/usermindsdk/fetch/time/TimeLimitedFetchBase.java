package com.usermind.usermindsdk.fetch.time;


import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import com.usermind.usermindsdk.fetch.samplefetch.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class TimeLimitedFetchBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLimitedFetchBase.class);

    protected final RunPoller runPoller;
    protected final MetadataFetch metadataFetch;
    protected RestTemplate restTemplate;

    @Autowired
    public TimeLimitedFetchBase(RestTemplate restTemplate, RunPoller runPoller, MetadataFetch metadataFetch) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.metadataFetch = metadataFetch;
    }

    public FetchedData runSampleFetch() throws NoSuchMethodException {
        //This is here so that we can catch the error in a base class and
        //transform it to a desired return error
        //TODO - catch and transform errors
        LOGGER.info("Running time limited fetch");
        return performTimeLimitedFetch();
     }

    abstract protected FetchedData performTimeLimitedFetch() throws NoSuchMethodException;

}
