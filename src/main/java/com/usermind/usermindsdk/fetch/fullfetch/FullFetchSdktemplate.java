package com.usermind.usermindsdk.fetch.fullfetch;

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
        Events events = (Events) metadataFetchSdktemplate.performMetadataFetch(incomingCredentials);

        SdktemplateCredentials credentials = SdktemplateCredentialDeserializer.deserialize(incomingCredentials);
        getAllRegistrations(events, credentials.getToken());
        return;
    }

    protected void getAllRegistrations(Events events, String apiKey) {
        events.getData().parallelStream()
                .map(event -> event.getLinks().getSelf() + "/registrations")
                .forEach(url -> getEventRegistrations(url, apiKey));
    }

    private void getEventRegistrations(String url, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, TOKEN_STRING + apiKey);
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        response.getBody();
    }

}
