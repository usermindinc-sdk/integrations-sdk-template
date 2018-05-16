package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

public abstract class FullFetchBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetchBase.class);

    protected final RunPoller runPoller;
    protected final MetadataFetch metadataFetch;

    protected final RestTemplate restTemplate;

    public FullFetchBase(RestTemplate restTemplate, RunPoller runPoller, MetadataFetch metadataFetch) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.metadataFetch = metadataFetch;
        return;
    }
    //Takes 8 to 9 minutes in the old code
    //New code:
    //Just getting data from Tito: 27 seconds
    //Parallel threads: 15 seconds

    //Creating the metrics class and reading the configuration data - also 15 seconds
    //Took 7 to set up metrics, 8 to read the config file

    public void runFullFetch() {
        //This is here so that we can catch the error in a base class and
        //transform it to a desired return error
        //TODO - catch and transform errors

        //For Tito - this is hard coded. Fetch the registrations:
        //https://api.tito.io/timeline
        //Then for each registration, fetch the attendees:
        //https://api.tito.io/v2/ragi-test/2016-edition/registrations
        //https://api.tito.io/v2/ragi-test/2017-edition/registrations

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        performFullFetch();

        stopWatch.stop();

        LOGGER.info("Full fetch took {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
        return;
    }

    abstract void performFullFetch();

}
