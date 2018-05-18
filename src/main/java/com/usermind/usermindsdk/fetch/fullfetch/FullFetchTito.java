package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchTito;
import com.usermind.usermindsdk.helpers.TitoCredentialDeserializer;
import com.usermind.usermindsdk.helpers.TitoCredentials;
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
public class FullFetchTito implements FullFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetchTito.class);

    private final RestTemplate restTemplate;
    private final MetadataFetchTito metadataFetchTito;

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_STRING = "Token token=";

    @Autowired
    public FullFetchTito(RestTemplate restTemplate, MetadataFetchTito metadataFetchTito) {
        this.restTemplate = restTemplate;
        this.metadataFetchTito = metadataFetchTito;
    }
    //Takes 8 to 9 minutes in the old code
    //New code:
    //Just getting data from Tito: 27 seconds
    //Parallel threads: 15 seconds

    //Creating the metrics class and reading the configuration data - also 15 seconds
    //Took 7 to set up metrics, 8 to read the config file

    @Override
    public void performFullFetch(String incomingCredentials) throws NoSuchMethodException {
        Events events = (Events) metadataFetchTito.performMetadataFetch(incomingCredentials);

        TitoCredentials credentials = TitoCredentialDeserializer.deserialize(incomingCredentials);
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
