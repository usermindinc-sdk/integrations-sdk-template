package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.helpers.CredentialContainerSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AddAuthenticationInformationSdktemplate implements AddAuthenticationInformation<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAuthenticationInformationSdktemplate.class);

    @Override
    public void putAuthIntoHeaderMap(CredentialContainerSdktemplate credentials, String link, Map<String, String> headers)throws Exception {
        //TODO - put authentication information into the headers
    }

}