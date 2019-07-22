package com.usermind.usermindsdk.authentication.credentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SdktemplateEntity extends Entity {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateEntity.class);

    //TODO - put in a field for each item in the entity (other than the ones in the base class!) and a getter for each
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
