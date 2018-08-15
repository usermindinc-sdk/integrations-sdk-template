package com.usermind.usermindsdk.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class CredentialDeserializerSdktemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialDeserializerSdktemplate.class);

    private static ObjectMapper objectMapper;

    @Autowired
    public CredentialDeserializerSdktemplate(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static CredentialsSdktemplate deserialize(String incomingCredentials) {
        checkNotNull(incomingCredentials);
        checkNotNull(objectMapper);
        //TODO - given a serialized credential string, deserialize it into the CredentialsSdktemplate POJO
        return null;
    }


}
