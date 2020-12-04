package com.usermind.usermindsdk.actions;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.usermind.usermindsdk.actions.drivers.ActionInput;
import com.usermind.usermindsdk.exceptions.SDKActionConfigurationFailedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    TODO: Make sure to change the names of this and all the other classes in this folder according
        to the type of Action you are performing instead of the generic CreateEntity
 */


@Component("CreateEntityInput")
@Scope("prototype")
public class SdktemplateCreateEntityInput implements ActionInput {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCreateEntityInput.class);

    /*
    This is the input class to go with SdktemplateCreateEntityAction. Note the names must be the same except one ends in Action and one ends
    in Input.

    You can leave it as is, and it will be automatically populated with a map. Or you can make it a pojo that matches the Action Body you
    define - we are deserializing the action using Jackson, so it just needs to be able to receive the fields.
     */
    private Map<String, Object> fields = new LinkedHashMap<>();

     @JsonAnySetter
    public void setField(String key, Object value) {
        fields.put(key, value);
    }
    //If you only have string inputs, you can change that to:
    //public void setField(String key, String value) {

    public Map<String, Object> getFields() {
        return fields;
    }


    /*
    But the preferred and easier way is for this just to be a pojo, for example:

    private String strValue;
    private boolean boolValue;
    private int intValue;

    //And to change the string from the input if necessary to make it a Java standard format:
    @JsonProperty("VALUE")
    private String value;

    @JsonProperty("value_two")
    private String valueTwo;


   If you make this a pojo you do not need the fields variable at all and can remove it. You can also mix and match.

     */



    @Override
    public void verify() {

        //Is there anything that can be verified about the input to ensure it's valid?
        //If so then check it here and throw SDKActionConfigurationFailedException if it is invalid.
        //Anything that fails this check will NOT be passed to the SDK.

        //But please throw the exception with a string saying exactly why you rejected this, as that
        //truly helps when looking at rejected actions!

        //If you have no verification you can just delete this method.

        if (StringUtils.isBlank((String)fields.get("requiredValue"))) {
            throw new SDKActionConfigurationFailedException("The required value was blank.");
        }

        //This is not really a sensible test but it's here more to have a second and different example.
        if ((int)fields.get("positive integer") < 0) {
            throw new SDKActionConfigurationFailedException("The positive integer was less than zero.");
        }

    }
}
