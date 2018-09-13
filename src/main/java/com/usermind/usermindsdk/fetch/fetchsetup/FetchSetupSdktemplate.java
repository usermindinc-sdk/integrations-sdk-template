package com.usermind.usermindsdk.fetch.fetchsetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.helpers.CredentialContainerSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class FetchSetupSdktemplate implements FetchSetup<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchSetupSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public FetchSetupSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }



    @Override
    public FetchSetupData performFullFetchSetup(CredentialContainerSdktemplate credentials, List<String> whitelist, List<String> blacklist) throws Exception {
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
    public FetchSetupData performIncrementalFetchSetup(CredentialContainerSdktemplate credentials, List<String> whitelist, List<String> blacklist, String startTime) throws Exception {
        LOGGER.info("Running fetchsetup incremental fetch - everything after {}", startTime);
        //TODO - implement incremental fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //See full fetch for more details. But this path should specify a path that will fetch everything AFTER the given time.
        fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);

        return fetchSetupData;
    }

    @Override
    public FetchSetupData performTimeLimitedFetchSetup(CredentialContainerSdktemplate credentials, List<String> whitelist, List<String> blacklist, String startTime, String endTime) throws Exception {
        LOGGER.info("Running fetchsetup time limited fetch - everything between {} and {}", startTime, endTime);
        //TODO - implement time limited fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //See full fetch for more details. But this path should specify a path that will fetch everything BETWEEN the two given times.
        fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);

        return fetchSetupData;
    }

    @Override
    public FetchSetupData performSampleFetchSetup(CredentialContainerSdktemplate credentials, List<String> whitelist, List<String> blacklist, Integer sampleSize) throws Exception {
        LOGGER.info("Running fetchsetup sample fetch");
        //TODO - implement sample fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //See full fetch for more details. But this path should specify a path that will fetch only about the number of records in "sampleSize".
        //That can be the latest (if a "top" command is available), it can be a range of sample records (one from each month for the last sampleSize months)
        //or it can be anything that makes sense given the APIs available.
        fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);

        return fetchSetupData;
    }

    @Override
    public FetchSetupData performMetadataFetchSetup(CredentialContainerSdktemplate credentials, List<String> whitelist, List<String> blacklist) throws Exception {
        LOGGER.info("Running fetchsetup metadata fetch");
        //TODO - implement metadata fetch
        FetchSetupData fetchSetupData = new FetchSetupData();
        HttpHeaders headers = new HttpHeaders();
        URI uri = new URI("https://www.somepath.com");
        //See full fetch for more details. But this path should specify a path that will fetch not data, but metadata -
        //what fields are in the response, and what type are they?
        fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);

        return fetchSetupData;
    }
}



