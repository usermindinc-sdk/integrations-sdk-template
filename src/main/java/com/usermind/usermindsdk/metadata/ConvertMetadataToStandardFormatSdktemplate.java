package com.usermind.usermindsdk.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConvertMetadataToStandardFormatSdktemplate implements ConvertMetadataToStandardFormat {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertMetadataToStandardFormatSdktemplate.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public ConvertMetadataToStandardFormatSdktemplate(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, MetadataRecords> processMetaData(String input) throws Exception {

        //If you hard coded the metadata information in MetadataFetchSetup, then just use this code and you're done with
        //this class.
        MetadataRecords records = objectMapper.readValue(input, MetadataRecords.class);
        Map<String, MetadataRecords> metadata = new HashMap<>();
        metadata.put(records.getEntity(), records);

        return metadata;

        //Otherwise metadata information was fetched from the integration. That means a call was made, as defined in
        //MetadataFetchSetup.
        //The results of that call will be sent in here, and will be in the Input string. This method then needs to
        //take that integration defined result and translate it into a format that Usermind can understand.
        //The MetadataRecords structure exists to hopefully make it easier to do that.


        //MetadataRecords is a class to hold the information for one entity. Return a map of them, with the
        //key being the entity name and the value being the MetadataRecords item.

        //MetadataRecords contains MetadataRecordItems, which are essentially some information about each data item in the entity.
        //Namely - is it a string or a number, is there a display string, etc.

        //Some sample pseudo code follows to give you the idea.

        //Map<String, MetadataRecords> allEntities = new HashMap<>();
        //Parse that string into a data object and then walk each entity in it -
        //MetadataRecords entityMetadata = processEntity(entityName, parsedOutEntity);
        //allEntities.put(entityName, entityMetadata);

        //return allEntities;
    }


//    private MetadataRecords processEntity(String entityName, IntegrationData integrationData) {
//        MetadataRecords metadataRecords = new MetadataRecords();
//        metadataRecords.setEntity(entityName);
//        metadataRecords.setPrimaryKey(integrationData.getPrimaryKey(entityName));
//        for (IntegrationItem integrationItem : integrationData) {
//            MetadataRecordItem metadataRecord = new MetadataRecordItem();
//            metadataRecord.setDisplayName(integrationItem.getAttribute("Name"));
//            metadataRecord.setFieldName(integrationItem.getAttribute("Name"));
//            metadataRecord.setType(integrationItem.getAttribute("Type"));
//            metadataRecord.setRequired(integrationItem.getAttribute("Nullable"));
//            metadataRecords.addRecord(metadataRecord);
//        }
//        return metadataRecords;
//    }
}
