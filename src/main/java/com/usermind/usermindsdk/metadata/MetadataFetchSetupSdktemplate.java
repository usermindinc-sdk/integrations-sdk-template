package com.usermind.usermindsdk.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import com.usermind.usermindsdk.fetch.fetchsetup.FetchSetup;
import com.usermind.usermindsdk.fetch.fetchsetup.FetchSetupData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class MetadataFetchSetupSdktemplate implements MetadataFetchSetup<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataFetchSetupSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final EntityInformation entityInformation;

    @Autowired
    public MetadataFetchSetupSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper,
                                         EntityInformation entityInformation) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.entityInformation = entityInformation;
    }

    @Override
    public FetchSetupData performMetadataFetchSetup(CredentialContainerSdktemplate credentials) throws Exception {
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



