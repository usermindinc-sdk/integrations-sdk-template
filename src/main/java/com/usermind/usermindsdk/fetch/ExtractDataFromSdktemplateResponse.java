package com.usermind.usermindsdk.fetch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.CredentialContainerSdktemplate;
import com.usermind.usermindsdk.fetch.structures.ExtractedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;

@Component
public class ExtractDataFromSdktemplateResponse implements ExtractDataFromResponse<CredentialContainerSdktemplate> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractDataFromSdktemplateResponse.class);

    private ObjectMapper objectMapper;

    public ExtractDataFromSdktemplateResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ExtractedData extractData(CredentialContainerSdktemplate credentials, String entityName,
                                     BufferedReader bufferedReader, BufferedWriter bufferedWriter, String rideAlong) {

        ExtractedData extractedData = new ExtractedData(bufferedWriter);

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

        //Call extractedData.addExtractedItem to write it, or else write to the buffer yourself - but don't forget to add
        //a newline if you do.

        /*
        PREFERRED PATH: Read lines one at a time from the buffered reader, write lines to the buffered writer as you go. This
        won't blow out the memory.

        UNDESIRED PATH: If the input is small or if reading it by line isn't feasible, then just put the bufferedreader into a string:
            String input = null;
            try {
                input = org.apache.commons.io.IOUtils.toString(bufferedReader);
            } catch (IOException e) {
                throw new SDKInvalidJsonException("Couldn't read the input!", e);
            }


        */

        return extractedData;
    }

}
