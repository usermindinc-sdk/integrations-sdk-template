package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneOffset;

/**
 * This class is to hold the session credentials. All access to those should be through the getSession call here. That call
 * will either return the cached credentials, but will also renew them if appropriate.
 */

/*
TODO: if you do not have sessions in your integration (look for the authentication type in the API)
    make sure to delete this class and the corresponding Unit/Integration Test classes in the test folder!
 */

@Component
public class SdktemplateSessionManager extends ConnectionSessionManager<SdktemplateConnectionData, SdktemplateSession>{

    public static final String SESSION_URI = "url to get the session";

    private SdktemplateSession csi;
    public static String SESSION_API = "https://base.api.to.call/oauth/authorize";
    private final RestTemplate restTemplate;

    @Autowired
    public SdktemplateSessionManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Validate credentials. But don't store them.
    @Override
    public SdktemplateSession validate(SdktemplateConnectionData sdktemplateConnectionData) throws Exception {
        return getNewSession(sdktemplateConnectionData);
    }

    protected SdktemplateSession getNewSession(SdktemplateConnectionData sdktemplateConnectionData) throws Exception {

        MultiValueMap<String, String> sessionRequestHeaders = new LinkedMultiValueMap<>();
        //TODO - add any headers needed to get a session
        sessionRequestHeaders.add("headerName", "headerValue");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(sessionRequestHeaders, headers);

        //And make the call to get a session
        ResponseEntity<JsonNode> response = restTemplate.exchange( SESSION_API , HttpMethod.POST, entity, JsonNode.class);

        //Get the JSON response and put it into a session object. The weird double call syntax is essentially so that the
        //call can get the correct class type for deserialization.
        SdktemplateSession sdktemplateSession = new SdktemplateSession();
        SdktemplateSession newSdktemplateSession = (SdktemplateSession)sdktemplateSession.getInstance(response.getBody());

        //And set the time this session expires - either calculate it, or else if it's sent back in the session response,
        //query the session response to get it.
        this.setExpirationTime(newSdktemplateSession.getExpiresIn());

        //To calculate it - for the example below, say the session will expire in two hours after
        //being issued for this integration type. Then:
        //this.setExpirationTime(Instant.ofEpochMilli(
        //        Long.parseLong(sdktemplateSession.getIssuedAt()))
        //        .plus(2, HOURS).atOffset(ZoneOffset.UTC));
        return newSdktemplateSession;
    }
}
