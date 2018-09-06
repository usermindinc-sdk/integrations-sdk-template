package com.usermind.usermindsdk.fetch.fetchsetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.helpers.CredentialDeserializerSdktemplate;
import com.usermind.usermindsdk.helpers.CredentialsSdktemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class FetchSetupSdktemplate implements FetchSetup {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchSetupSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public FetchSetupSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }



    @Override
    public FetchSetupData performFullFetchSetup(String incomingCredentials, List<String> whitelist, List<String> blacklist) throws NoSuchMethodException {
        LOGGER.info("Running fetchsetup full fetch");
        CredentialsSdktemplate credentials = CredentialDeserializerSdktemplate.deserialize(incomingCredentials);

        FetchSetupData fetchSetupData = new FetchSetupData();
        try {
            HttpHeaders headers = new HttpHeaders();
            URI uri = new URI("https://www.somepath.com");
            //Those should be headers and the URI to do the full fetch of some entity. This should
            //be in a loop so that it adds a web request for each entity desired.
            //If it has data that is NOT a web request but just is actual results as well,
            //then call addWriteRequest instead of addWebRequest, giving it the entity name and the data to write as a string.
            fetchSetupData.addWebRequest("EntityName", uri.toString(), headers);
        } catch (Exception e) {
            LOGGER.error("Didn't work! Replace this with a custom exception and change the message signature!", e);
            //TODO just throw an appropriately mapped exception
            return null;
        }
        return fetchSetupData;
    }


    @Override
    public FetchSetupData performIncrementalFetchSetup(String incomingCredentials, List<String> whitelist, List<String> blacklist, String startTime) throws Exception {
        LOGGER.info("Running fetchsetup incremental fetch - everything after {}", startTime);
        CredentialsSdktemplate credentials = CredentialDeserializerSdktemplate.deserialize(incomingCredentials);

        FetchSetupData fetchSetupData = new FetchSetupData();
         try {
            HttpHeaders headers = new HttpHeaders();
            URI uri = new URI("https://www.somepath.com");
            //See full fetch for more details. But this path should specify a path that will fetch everything AFTER the given time.
            fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);
        } catch (Exception e) {
            LOGGER.error("Didn't work! Replace this with a custom exception and change the message signature!", e);
            //TODO just throw an appropriately mapped exception
            return null;
        }
        return fetchSetupData;
    }

    @Override
    public FetchSetupData performTimeLimitedFetchSetup(String incomingCredentials, List<String> whitelist, List<String> blacklist, String startTime, String endTime) throws Exception {
        LOGGER.info("Running fetchsetup time limited fetch - everything between {} and {}", startTime, endTime);
        CredentialsSdktemplate credentials = CredentialDeserializerSdktemplate.deserialize(incomingCredentials);

        FetchSetupData fetchSetupData = new FetchSetupData();
        try {
            HttpHeaders headers = new HttpHeaders();
            URI uri = new URI("https://www.somepath.com");
            //See full fetch for more details. But this path should specify a path that will fetch everything BETWEEN the two given times.
            fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);
        } catch (Exception e) {
            LOGGER.error("Didn't work! Replace this with a custom exception and change the message signature!", e);
            //TODO just throw an appropriately mapped exception
            return null;
        }
        return fetchSetupData;
    }

    @Override
    public FetchSetupData performSampleFetchSetup(String incomingCredentials, List<String> whitelist, List<String> blacklist, Integer sampleSize) throws Exception {
        LOGGER.info("Running fetchsetup sample fetch");
        CredentialsSdktemplate credentials = CredentialDeserializerSdktemplate.deserialize(incomingCredentials);

        FetchSetupData fetchSetupData = new FetchSetupData();
        try {
            HttpHeaders headers = new HttpHeaders();
            URI uri = new URI("https://www.somepath.com");
            //See full fetch for more details. But this path should specify a path that will fetch only about the number of records in "sampleSize".
            //That can be the latest (if a "top" command is available), it can be a range of sample records (one from each month for the last sampleSize months)
            //or it can be anything that makes sense given the APIs available.
            fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);
        } catch (Exception e) {
            LOGGER.error("Didn't work! Replace this with a custom exception and change the message signature!", e);
            //TODO just throw an appropriately mapped exception
            return null;
        }
        return fetchSetupData;
    }

    @Override
    public FetchSetupData performMetadataFetchSetup(String incomingCredentials, List<String> whitelist, List<String> blacklist) throws Exception {
        LOGGER.info("Running fetchsetup metadata fetch");
        CredentialsSdktemplate credentials = CredentialDeserializerSdktemplate.deserialize(incomingCredentials);

        FetchSetupData fetchSetupData = new FetchSetupData();
        try {
            HttpHeaders headers = new HttpHeaders();
            URI uri = new URI("https://www.somepath.com");
            //See full fetch for more details. But this path should specify a path that will fetch not data, but metadata -
            //what fields are in the response, and what type are they?
            fetchSetupData.addWebRequest("ObjectName", uri.toString(), headers);
        } catch (Exception e) {
            LOGGER.error("Didn't work! Replace this with a custom exception and change the message signature!", e);
            //TODO just throw an appropriately mapped exception
            return null;
        }
        return fetchSetupData;
    }
}



