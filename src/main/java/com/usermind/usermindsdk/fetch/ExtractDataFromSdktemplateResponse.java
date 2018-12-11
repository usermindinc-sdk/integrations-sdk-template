package com.usermind.usermindsdk.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import com.usermind.usermindsdk.fetch.structures.ExtractedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExtractDataFromSdktemplateResponse implements ExtractDataFromResponse<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractDataFromSdktemplateResponse.class);

    private ObjectMapper objectMapper;

    public ExtractDataFromSdktemplateResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExtractedData extractData(CredentialContainerSdktemplate credentials, String entityName, String input, String rideAlong) {

        ExtractedData extractedData = new ExtractedData();

        //The given URL from FetchSetup was called. Something was fetched back. This method is here to get that
        //integration formatted data into a form that Usermind understands.

        //entityName tells you what the entity is
        //input is the actual response from the integration
        //And sometimes data is associated with this call that is needed - if so, then setup can put it in the ridealong string,
        //and it will be retained here.

        //In essence - the entity data is often embedded in a data structure. Just split it out so that instead of a long list,
        //you have separated each record into a different data structure.

       //So given {"value": [A], [B] ...}
        //return a list with A, B, etc.
        //The DataType tells you which entity this is, so that you will know what data you are extracting.

        return extractedData;
    }

}
