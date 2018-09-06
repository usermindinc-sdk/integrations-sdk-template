package com.usermind.usermindsdk.workerinformation;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class EntityInformationSdktemplate extends EntityInformation {

    public EntityInformationSdktemplate() {
        initializeMap();
    }

    private void initializeMap() {
        Map<String, EntityInformationItem> tempEntityKeys = new HashMap<>();

        entityInformation = Collections.unmodifiableMap(tempEntityKeys);
    }


}
