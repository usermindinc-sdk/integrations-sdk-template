package com.usermind.usermindsdk.fetch.metadatafetch;

import com.usermind.usermindsdk.fetch.metadatafetch.EntityInformation;
import com.usermind.usermindsdk.fetch.metadatafetch.EntityInformationItem;
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

        //Now fill the map in with items telling us the what field is the primary key for this data, and if it is going to support incremental fetches then the second field is
        //the data item that is a date we can use when querying for only the new entries (ie, record the time of the last fetch, and then use this field to filter on that timestamp.)
        //tempEntityKeys.put(FetchSetupSdktemplate.TEMPLATE_LIST, new EntityInformationItem("template_id", ""));
        entityInformation = Collections.unmodifiableMap(tempEntityKeys);
    }


}
