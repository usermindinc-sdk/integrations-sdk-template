package com.usermind.usermindsdk.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
import com.usermind.usermindsdk.metadata.EntityInformation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FetchSetupSdktemplate implements FetchSetup<SdktemplateConnectionData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchSetupSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final EntityInformation entityInformation;

    @Autowired
    public FetchSetupSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper,
                                 EntityInformation entityInformation) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.entityInformation = entityInformation;
    }

    /*

    First, see MetadataFetchSetupSdktemplate for examples. This works in exactly the same way.
    It is not doing fetches, but it's sending instructions on how to do the fetches to the orchestration layer.

    So send in write requests, web requests, or calls to libraries in the same way as MetadataFetchSetupSdktemplate.

     */

    @Override
    public FetchSetupData performFullFetchSetup(SdktemplateConnectionData sdktemplateConnectionData, String entity) throws Exception {
        LOGGER.info("Running fetchsetup full fetch for {}", entity);
        FetchSetupData fetchSetupData = new FetchSetupData();
        return fetchSetupData;
    }


    @Override
    public FetchSetupData performIncrementalFetchSetup(SdktemplateConnectionData sdktemplateConnectionData, String entity, String startTime) throws Exception {
        LOGGER.info("Running fetchsetup incremental fetch for {} - everything after {}", entity, startTime);

        //If there isn't an incremental field, then just do a full fetch.
        if (StringUtils.isEmpty(entityInformation.getDateFieldForIncrementalFetch(entity))) {
            return performFullFetchSetup(sdktemplateConnectionData, entity);
        }

        throw new NoSuchMethodException("Incremental fetch is not supported for Sdktemplate!");
    }

    @Override
    public FetchSetupData performTimeLimitedFetchSetup(SdktemplateConnectionData sdktemplateConnectionData, String entity, String startTime, String endTime) throws Exception {
        LOGGER.info("Running fetchsetup time limited fetch for {} - everything between {} and {}", entity, startTime, endTime);
        throw new NoSuchMethodException("Time limited fetch is not supported for Sdktemplate!");
    }

    @Override
    public FetchSetupData performSampleFetchSetup(SdktemplateConnectionData sdktemplateConnectionData, String entity, Integer sampleSize) throws Exception {
        LOGGER.info("Running fetchsetup sample fetch for {}", entity);
        throw new NoSuchMethodException("Sample fetch is not supported for Sdktemplate!");
     }

}



