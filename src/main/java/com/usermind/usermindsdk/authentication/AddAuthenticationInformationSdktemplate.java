package com.usermind.usermindsdk.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddAuthenticationInformationSdktemplate implements AddAuthenticationInformation<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAuthenticationInformationSdktemplate.class);

    @Override
    public String putAuthIntoWebRequest(CredentialContainerSdktemplate credentials, String link, Map<String, String> headers)throws Exception {
        //TODO - put authentication information into the headers
        //Some integrations add them to the link directly instead of the headers. The link returned is therefore the link used, NOT the
        //one sent in - this lets you add authentication to that as well.

        return link;
    }

}
