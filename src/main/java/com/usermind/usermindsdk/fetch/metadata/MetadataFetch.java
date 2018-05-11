package com.usermind.usermindsdk.fetch.metadata;


import com.usermind.usermindsdk.fetch.json.events.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;

@Component
public class MetadataFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetch.class);

    private RestTemplate restTemplate;

    @Autowired
    public MetadataFetch(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        return;
    }

    public Events runMetadataFetch(String accountName, String apiKey) {
        //For Tito - this is hard coded. Fetch the registrations:
        //https://api.tito.io/timeline
        UriBuilder singleFieldBuilder = UriBuilder
                .fromPath("https://api.tito.io")
                .path("/v2/" + accountName + "/events");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token token=" + apiKey);
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Events> response = restTemplate.exchange(singleFieldBuilder.build(), HttpMethod.GET, entity, Events.class);

        return response.getBody();
    }



}
