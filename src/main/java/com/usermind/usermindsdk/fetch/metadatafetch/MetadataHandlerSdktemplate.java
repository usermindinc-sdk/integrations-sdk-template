package com.usermind.usermindsdk.fetch.metadatafetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MetadataHandlerSdktemplate implements MetadataHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataHandlerSdktemplate.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public MetadataHandlerSdktemplate(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, MetadataRecords> processMetaData(String input) throws Exception {

        Map<String, MetadataRecords> allEntities = new HashMap<>();
        //This is given metadata as an input string - whatever we get from the result of the MetadataFetchSetup call.
        //Parse it, and return it broken up into the format specified in MetadataRecords.

        //That is most easily done by taking each field type and creating a MetadataRecordItem for it, which takes:
        //A display name,
        //Whether it is a required field if anything is written back to that record,
        //If it's read only or not,
        //The type (as specified in an enum.)

        //then just call add on metaDataRecords with the entity name and that MetadataRecordItem




        return allEntities;
    }

}
