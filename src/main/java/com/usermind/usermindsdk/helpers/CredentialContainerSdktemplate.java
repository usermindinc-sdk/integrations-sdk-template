package com.usermind.usermindsdk.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CredentialContainerSdktemplate extends CredentialContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialContainerSdktemplate.class);

    public CredentialContainerSdktemplate(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    //TODO - give the class helper methods to get the authentication information
    //The base class has a get call which just reads the JSON for a specified key, or
    //else write your own call to get information.
    //For example:
//    public String getApiId() {
//        return get("apiId");
//    }

//TODO - Put the different fields into the registration configuration file
    //It is in src/main/resources/com/usermind/usermindsdk/registration/IntegrationConfiguration.json
    //In that file just modify the keys section. It is currently:

//    "keys":{
//    "apiId":{  <--field in the license information
//        "displayName":"API Key", <--human readable name
//         "hidden":false
//    },
//    "accountName":{  <--field in the license information
//        "displayName":"Account Name", <--human readable name
//        "hidden":false
//    }

    //For each field in your key, add a section.

}
