package com.usermind.usermindsdk.fetch.fetchoperations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExtractDataFromSdktemplateResponse implements ExtractDataFromResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractDataFromSdktemplateResponse.class);

    private ObjectMapper objectMapper;

    public ExtractDataFromSdktemplateResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<String> extractData(String dataType, String input) {

        List<String> results = new ArrayList<>();

        //TODO: Given a list of results, pull the individual result lines out.
        //So given {"value": [A], [B] ...}
        //return a list with A, B, etc.
        //The DataType tells you which entity this is, so that you will know what data you are extracting.

        return results;
    }

}
