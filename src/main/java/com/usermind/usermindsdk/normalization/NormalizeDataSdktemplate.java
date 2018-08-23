package com.usermind.usermindsdk.normalization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class NormalizeDataSdktemplate implements NormalizeData {

    private static final Logger LOGGER = LoggerFactory.getLogger(NormalizeDataSdktemplate.class);
    public static final String tokenizer = "____";

    private final ObjectMapper objectMapper;

    @Autowired
    public NormalizeDataSdktemplate(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String normalizeData(String dataDescriptor, String rawInput) {
        checkNotNull(dataDescriptor);
        checkNotNull(rawInput);

        //For now ... just shoot it right back.
        return rawInput;
    }

}
