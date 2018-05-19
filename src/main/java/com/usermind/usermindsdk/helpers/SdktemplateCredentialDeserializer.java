package com.usermind.usermindsdk.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class SdktemplateCredentialDeserializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCredentialDeserializer.class);

    private static ObjectMapper objectMapper;

    @Autowired
    public SdktemplateCredentialDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static SdktemplateCredentials deserialize(String incomingCredentials) {
        checkNotNull(incomingCredentials);
        checkNotNull(objectMapper);
        try {
            return objectMapper.readValue(incomingCredentials, SdktemplateCredentials.class);
        } catch (IOException e) {
            LOGGER.error("Unable to deserialize Sdktemplate credential string {}", incomingCredentials);
        }
        return null;
    }


}
