package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SessionCredentialsSdktemplate {

    public static final String SESSION_PATH = "/services/api/sts/session";

    private SessionInformationSdktemplate csi;

    public SessionInformationSdktemplate getSession(CredentialContainerSdktemplate credentials) throws Exception {

        if (csi == null || csi.shouldRenew()) {
            csi = getNewSession(credentials);
            return csi;
        }

        return csi;
    }

    private SessionInformationSdktemplate getNewSession(CredentialContainerSdktemplate credentials) throws Exception {

        //TODO - make a call to the integration to get the session here.
        return null;
    }

}
