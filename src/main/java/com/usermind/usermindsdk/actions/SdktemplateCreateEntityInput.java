package com.usermind.usermindsdk.actions;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.usermind.usermindsdk.actions.drivers.ActionInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

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

    public Map<String, Object> getFields() {
        return fields;
    }
}
