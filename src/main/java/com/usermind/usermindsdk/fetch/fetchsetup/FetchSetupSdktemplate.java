package com.usermind.usermindsdk.fetch.fetchsetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import com.usermind.usermindsdk.metadata.EntityInformation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class FetchSetupSdktemplate implements FetchSetup<CredentialContainerSdktemplate> {
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

    @Override
    public FetchSetupData performFullFetchSetup(CredentialContainerSdktemplate credentials, String entity) throws Exception {
        LOGGER.info("Running fetchsetup full fetch");
        //TODO - implement full fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //Those should be headers and the URI to do the full fetch of some entity. This should
        //be in a loop so that it adds a web request for each entity desired.
        //If it has data that is NOT a web request but just is actual results as well,
        //then call addWriteRequest instead of addWebRequest, giving it the entity name and the data to write as a string.
        fetchSetupData.addWebRequest("EntityName", uri.toString(), headers);
        return fetchSetupData;
    }


    @Override
    public FetchSetupData performIncrementalFetchSetup(CredentialContainerSdktemplate credentials, String entity, String startTime) throws Exception {
        LOGGER.info("Running fetchsetup incremental fetch - everything after {}", startTime);

        if (StringUtils.isEmpty(entityInformation.getDateFieldForIncrementalFetch(entity))) {
            return performFullFetchSetup(credentials, entity);
        }

        //TODO - implement incremental fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //See full fetch for more details. But this path should specify a path that will fetch everything AFTER the given time.
        //If you can't, then just pass the call through to full fetch.
        fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);

        return fetchSetupData;
    }

    @Override
    public FetchSetupData performTimeLimitedFetchSetup(CredentialContainerSdktemplate credentials, String entity, String startTime, String endTime) throws Exception {
        LOGGER.info("Running fetchsetup time limited fetch - everything between {} and {}", startTime, endTime);
        throw new NoSuchMethodException("Time limited fetch is not supported for Sdktemplate!");
        //TODO - implement time limited fetch
    }

    @Override
    public FetchSetupData performSampleFetchSetup(CredentialContainerSdktemplate credentials, String entity, Integer sampleSize) throws Exception {
        LOGGER.info("Running fetchsetup sample fetch");
        throw new NoSuchMethodException("Sample fetch is not supported for Sdktemplate!");
        //TODO - implement sample fetch
     }

}



