package com.usermind.usermindsdk.fetch.time;


import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import com.usermind.usermindsdk.fetch.samplefetch.SampleData;
import com.usermind.usermindsdk.fetch.samplefetch.SampleFetchBase;
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
public class TimeLimitedFetch extends TimeLimitedFetchBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLimitedFetch.class);

    @Autowired
    public TimeLimitedFetch(RestTemplate restTemplate, RunPoller runPoller, MetadataFetch metadataFetch) {
        super(restTemplate, runPoller, metadataFetch);
        return;
    }

    public FetchedData performTimeLimitedFetch() throws NoSuchMethodException {
        LOGGER.info("Running time limited fetch");
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }


}
