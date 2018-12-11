package com.usermind.usermindsdk.metadata;

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
        Map<String, EntityInformationItem> entityKeys = new HashMap<>();

        //Now fill the map in with items telling us the what field is the primary key for this data, and if it is going to
        //support incremental fetches then the second field is the data item that is a date we can use when querying
        //for only the new entries (ie, record the time of the last fetch, and then use this field to filter on that timestamp.)
        //If this entity doesn't support incremental fetches, leave it blank.

        //This then will consist of one line for each entity. It is of this format:
        //entityKeys.put("Entity Name", new EntityInformationItem("Primary Key", "Field to use for incremental fetch"));

        //Example for an entity that does support incremental fetching:
        //entityKeys.put(FetchSetupSdktemplate.Registrations, new EntityInformationItem("registrationID", "registrationDateStamp"));

        //Example for an entity that does not support incremental fetching:
        //entityKeys.put(FetchSetupSdktemplate.USERNAMES, new EntityInformationItem("userID", ""));

        entityInformationItems = Collections.unmodifiableMap(entityKeys);
    }


}
