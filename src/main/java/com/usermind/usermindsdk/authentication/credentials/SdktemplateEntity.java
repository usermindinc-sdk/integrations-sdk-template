package com.usermind.usermindsdk.authentication.credentials;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Dont't Delete this class. We need this class to create Bean at runtime.
 */
@Component
@Scope("prototype")
public class SdktemplateEntity extends Entity {

    public SdktemplateEntity() {
        super();
    }

    //TODO: FYI - This is the placeholder to extend any entity properties other than standard ones which are given in the base
    // class 'Entity'.
    // Base class 'Entity' has below properties
    //    1. entityName - Name of an entity
    //    2. primaryKey - Primary key of an entity. Optional field
    //    3. incrementalField - Field that is used for incremental fetch. Optional field. In case if we don't mention it, then we gonna fetch the data fully all the time


    //TODO:  put in a field for each item in the entity (other than the ones in the base class!) and a getter for each
    // If we don't need any new properties, then we can delete this class.

//    For example: lets assume, we want to fetch data from a database table.
//    So we need to provide name of a schema. Then we can add schema property in this class like below

//    private String schema = "";
//
//    public String getSchema() {
//        return schema;
//    }
}
