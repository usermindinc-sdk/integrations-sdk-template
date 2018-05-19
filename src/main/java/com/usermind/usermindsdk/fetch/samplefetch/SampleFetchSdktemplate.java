package com.usermind.usermindsdk.fetch.samplefetch;


import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetchSdktemplate;
import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchSdktemplate;
import com.usermind.usermindsdk.helpers.SdktemplateCredentialDeserializer;
import com.usermind.usermindsdk.helpers.SdktemplateCredentials;
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
        Events events = (Events) metadataFetchSdktemplate.performMetadataFetch(incomingCredentials);

        SdktemplateCredentials credentials = SdktemplateCredentialDeserializer.deserialize(incomingCredentials);
        return getSomeRegistrations(events, credentials.getToken());
    }

    protected FetchedData getSomeRegistrations(Events events, String apiKey) {

        SampleData sampleData = new SampleData();

        //Limit ourselves to about 20 items. So walk the array in Events using a step size that will
        //end up giving us about 20.
        int stepsize = findStepSize(events.getData().size());
        List<DataItem> items = events.getData();

        for (int i = 0; i < events.getData().size(); i += stepsize ) {
            DataItem dataItem = items.get(i);
            Registrations registrations = getEventRegistrations(dataItem, apiKey);
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

    private Registrations getEventRegistrations(DataItem dataItem, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(FullFetchSdktemplate.AUTHORIZATION, FullFetchSdktemplate.TOKEN_STRING + apiKey);
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = dataItem.getLinks().getSelf() + "/registrations";
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        return response.getBody();
    }



}
