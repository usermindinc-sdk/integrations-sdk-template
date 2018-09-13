package com.usermind.usermindsdk.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CredentialContainerSdktemplate extends CredentialContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialContainerSdktemplate.class);

    public CredentialContainerSdktemplate(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    //TODO - give the class helper methods to get the authentication information

}
