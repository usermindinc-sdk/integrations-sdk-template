package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * This class is to hold the session credentials. All access to those should be through the getSession call here. That call
 * will either return the cached credentials, but will also renew them if appropriate.
 */
@Component
public class SessionCredentialManagerSdktemplate {

//    public static final String SESSION_PATH = "url to get the session";

    private SessionCredentialContainerSdktemplate csi;

    //Keep this method as is
    public SessionCredentialContainerSdktemplate getSession(CredentialContainerSdktemplate credentials,
                                                            RestTemplate restTemplate, ObjectMapper objectMapper) throws Exception {

        if (csi == null || csi.shouldRenew()) {
            csi = getNewSession(credentials, restTemplate, objectMapper);
            return csi;
        }

        return csi;
    }

    private SessionCredentialContainerSdktemplate getNewSession(CredentialContainerSdktemplate credentials,
                                                                RestTemplate restTemplate, ObjectMapper objectMapper) throws Exception {

//        URI uri = generateURIForSession(credentials);
//        HttpHeaders headers = generateHeadersForSession(credentials, SESSION_PATH, "POST");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode.class);
//
//        JsonNode dataNode = response.getBody();
        SessionCredentialContainerSdktemplate session = new SessionCredentialContainerSdktemplate(objectMapper);
//        session.load(dataNode);
        return session;
    }

//    protected URI generateURIForSession(CredentialContainerSdktemplate credentials) {
//        return UriBuilder
//                .fromPath(SESSION_PATH)
//                .queryParam("grant_type", "client_credential")
//                .queryParam("appid", credentials.getAppId())
//                .queryParam("secret", credentials.getAppSecret())
//                .build();
//    }

}
