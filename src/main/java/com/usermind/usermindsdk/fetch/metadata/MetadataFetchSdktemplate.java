package com.usermind.usermindsdk.fetch.metadata;

import com.usermind.usermindsdk.fetch.fullfetch.FullFetchSdktemplate;
import com.usermind.usermindsdk.fetch.json.events.Events;
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

import javax.ws.rs.core.UriBuilder;

@Component
public class MetadataFetchSdktemplate implements MetadataFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetchSdktemplate.class);

    private final RestTemplate restTemplate;

    @Autowired
    public MetadataFetchSdktemplate(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
    }

    @Override
    public MetadataFetchData performMetadataFetch(String incomingCredentials) {
         LOGGER.debug("Running metadata fetch");

        SdktemplateCredentials credentials = SdktemplateCredentialDeserializer.deserialize(incomingCredentials);

        UriBuilder singleFieldBuilder = UriBuilder
                .fromPath("https://api.tito.io")
                .path("/v2/" + credentials.getAccountName() + "/events");

        HttpHeaders headers = new HttpHeaders();
        headers.add(FullFetchSdktemplate.AUTHORIZATION, FullFetchSdktemplate.TOKEN_STRING + credentials.getToken());
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Events> response = restTemplate.exchange(singleFieldBuilder.build(), HttpMethod.GET, entity, Events.class);

        return response.getBody();
    }



}
