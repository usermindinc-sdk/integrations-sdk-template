package com.usermind.usermindsdk.fetch.fetchoperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExtractDataFromSdktemplateResponse implements ExtractDataFromResponse<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractDataFromSdktemplateResponse.class);

    private ObjectMapper objectMapper;

    public ExtractDataFromSdktemplateResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExtractedData extractData(CredentialContainerSdktemplate credentials, String entityName, String input, boolean metadata, String rideAlong) {

        ExtractedData extractedData = new ExtractedData();

        //TODO: Given a list of results, pull the individual result lines out.
        //So given {"value": [A], [B] ...}
        //return a list with A, B, etc.
        //The DataType tells you which entity this is, so that you will know what data you are extracting.

        return extractedData;
    }

}
