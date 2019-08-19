package com.usermind.usermindsdk.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

@Component
public class MetadataFetchSetupSdktemplate implements MetadataFetchSetup<SdktemplateConnectionData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetchSetupSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final EntityInformation entityInformation;

    //Put the entity names here for common use
//    public static final String DIRECT_WRITE_ENTITY = "DirectWrite";
//    public static final String WEB_REQUEST_ENTITY = "WebRequest";

    @Autowired
    public MetadataFetchSetupSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper,
                                         EntityInformation entityInformation) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.entityInformation = entityInformation;
    }

    @Override
    public FetchSetupData performMetadataFetchSetup(SdktemplateConnectionData sdktemplateConnectionData) throws Exception {
        LOGGER.info("Running fetchsetup metadata fetch");


        //This is NOT fetching the metadata. This is giving instructions to the orchestration layer so that IT can
        //fetch the metadata.

        //You can send instructions to do any of three things.
        //1 - Just send data directly (ie, don't do a fetch, just hard code metadata and pass it along)
        //2 - Call a Rest Api
        //3 - Call a client library which will do something

        //You can do multiple of each, so if you need to call a different Rest API for each entity, plus pass along
        //some data and call five client libraries, just send in instructions on how to do each.
        FetchSetupData fetchSetupData = new FetchSetupData();

        //1 - Write data directly
        //MetadataRecords directWriteMetadata = getDirectWriteMetadata();
        //fetchSetupData.addWriteRequest(DIRECT_WRITE_ENTITY, objectMapper.writer().writeValueAsString(directWriteMetadata));

        //2 - Call a Rest Api
        //URI uri = generateURI(see the call below)
        //HttpHeaders headers =  new HttpHeaders();
        //Add any headers needed, of course.
        //fetchSetupData.addWebRequest(WEB_REQUEST_ENTITY, uri.toString(), headers);

        //3 - Call a client library
        //Example coming

        return fetchSetupData;
    }

//      SUPPORT METHOD FOR 1 - Writing Data Directly
//    private MetadataRecords getDirectWriteMetadata() {
//        MetadataRecords metaDataRecords = new MetadataRecords();
//        metaDataRecords.setEntity(DIRECT_WRITE_ENTITY);
//        metaDataRecords.setPrimaryKey("UserId");
//        metaDataRecords.setAccessible(true);
//
//          Add a record for each field in the entity so we know how to display it, etc.
//          Arguments are the field name, the string to display for it, if it is required (ie, can't be empty),
//             if it's readonly or not, and the type - string, number, etc (as controlled by the enum)
//        metaDataRecords.addRecord(new MetadataRecordItem("UserId", "User Id", true, false,
//                MetadataTypes.NUMBER));
//        metaDataRecords.addRecord(new MetadataRecordItem("EmailAddress", "Email Address",true, false,
//                MetadataTypes.STRING));
//
//
//        return metaDataRecords;
//    }


    public static URI generateURI(SdktemplateConnectionData sdktemplateConnectionData, String path, String fragment, Map<String, String> queryParams) {
        UriBuilder builder = UriBuilder
                .fromPath("www.example.com")
                .scheme("http")
                .path("example/path");

        //The fragment is anything that should come after a #:
        //https://test.com/example#fragment
        if (!StringUtils.isEmpty(fragment)) {
            builder = builder.fragment(fragment);
        }

        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

}



