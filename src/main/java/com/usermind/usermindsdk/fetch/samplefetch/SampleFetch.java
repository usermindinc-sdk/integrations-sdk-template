package com.usermind.usermindsdk.fetch.samplefetch;


import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.apache.commons.lang3.time.StopWatch;
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
import java.util.concurrent.TimeUnit;

@Component
public class SampleFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleFetch.class);

    private final WorkerConfiguration workerConfiguration;
    private final RunPoller runPoller;
    private final WorkerInfo workerInfo;
    private final MetadataFetch metadataFetch;

    public static final String AUTHORIZATION = "Authorization";

    private RestTemplate restTemplate;

    @Autowired
    public SampleFetch(RestTemplate restTemplate, WorkerConfiguration workerConfiguration,
                       RunPoller runPoller, WorkerInfo workerInfo, MetadataFetch metadataFetch) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.workerInfo = workerInfo;
        this.metadataFetch = metadataFetch;
        this.workerConfiguration = workerConfiguration;
        return;
    }
    //Takes 8 to 9 minutes in the old code
    //New code:
    //Just getting data from Tito: 27 seconds
    //Parallel threads: 15 seconds

    //Creating the metrics class and reading the configuration data - also 15 seconds
    //Took 7 to set up metrics, 8 to read the config file

    public SampleData runSampleFetch() {
        //For Tito - this is hard coded. Fetch the registrations:
        //https://api.tito.io/timeline
        //Then for each registration, fetch the attendees:
        //https://api.tito.io/v2/ragi-test/2016-edition/registrations
        //https://api.tito.io/v2/ragi-test/2017-edition/registrations

        Events events = metadataFetch.runMetadataFetch(runPoller.getAccountName(), runPoller.getApiKey());
        return getSomeRegistrations(events);
    }

    protected SampleData getSomeRegistrations(Events events) {

        SampleData sampleData = new SampleData();

        //Limit ourselves to about 20 items. So walk the array in Events using a step size that will
        //end up giving us about 20.
        int stepsize = findStepSize(events.getData().size());
        List<DataItem> items = events.getData();

        for (int i = 0; i < events.getData().size(); i += stepsize ) {
            DataItem dataItem = items.get(i);
            Registrations registrations = getEventRegistrations(dataItem, sampleData);
            if (registrations != null) {
                sampleData.addItem(dataItem, registrations);
            }
        }
        return sampleData;
    }

    protected int findStepSize(int arraySize) {
        int stepSize = arraySize / 20;
        if (stepSize < 1) {
            return 1;
        }
        return stepSize;
    }

    private Registrations getEventRegistrations(DataItem dataItem, SampleData sampleData) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token token=" + runPoller.getApiKey());
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = dataItem.getLinks().getSelf() + "/registrations";
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        return response.getBody();
    }



}
