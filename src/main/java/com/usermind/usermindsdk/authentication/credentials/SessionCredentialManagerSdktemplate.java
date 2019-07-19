package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This class is to hold the session credentials. All access to those should be through the getSession call here. That call
 * will either return the cached credentials, but will also renew them if appropriate.
 */
@Component
public class SessionCredentialManagerSdktemplate {

    public static final String SESSION_URI = "url to get the session";

    private SessionCredentialContainerSdktemplate csi;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SessionCredentialManagerSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    //Keep this method as is
    public SessionCredentialContainerSdktemplate getSession(SdktemplateConnectionData credentials) throws Exception {

        if (csi == null || csi.shouldRenew()) {
            csi = getNewSession(credentials);
            return csi;
        }

        return csi;
    }

    //Validate credentials. But don't store them.
    public SessionCredentialContainerSdktemplate validate(SdktemplateConnectionData credentials) throws Exception {
        return getNewSession(credentials);
    }

    private SessionCredentialContainerSdktemplate getNewSession(SdktemplateConnectionData credentials) throws Exception {

//        URI uri = generateURIForSession(credentials);
//        HttpHeaders headers = generateHeadersForSession(credentials, SESSION_URI, "POST");
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
//                .fromPath(SESSION_URI)
//                .queryParam("grant_type", "client_credential")
//                .queryParam("appid", credentials.getAppId())
//                .queryParam("secret", credentials.getAppSecret())
//                .build();
//    }

}
