package com.usermind.usermindsdk.authentication.credentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*TODO -
 * This class should be used for integration which uses Authorization type: Key Value Pair
 * if we are using this class then delete the class - SdktemplateConnectionData1
 */
@Component
@Scope("prototype")
public class SdktemplateConnectionData extends ConnectionData<SdktemplateEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateConnectionData.class);

    //TODO - add properties of the connection with appropriate annotations
//    if we need username and password to make a http call to get any required data,
//    then add both the properties here.
//     There are two annotations available
//     1. @UserInputAnnotation - if we want any properties to be taken from UI.
//       - This annotation has three properties
//       - a. Display Name -  some user friendly name which will be shown on the UI input box
//       - b. hidden -  this field is used to hide the user input entires on the UI. By default its set to false.
//                      Example, anything we type for password entry will be shown as hidden values
//       - c. toolTip -  this is to give elaborated detail about the field. If we want to give a sample input entry for the given key, we can mention here.
//
//     2. @CredentialsAnnotation - this field is used to tell system to save the respective keys with encryption in our system.
//                               - also its possible that some of the keys are computed ones (ex : accessToken) and which are not taken from UI.
//                               - for such fields we can add this annotations.
//    For example:

    @UserInputAnnotation(displayName = "User Name",    //<----- User friendly name
            toolTip = "Login credentials - username"   //<----- This is an optional field, if we want to give more details about the property, we can write anything here
            )
    @CredentialsAnnotation()
    private String username;


    @UserInputAnnotation(displayName = "Password",
            hidden = true,                             //<----- If its set to true, whatever the value we enter on the UI will not be visible as plain text
            toolTip = "The password"
            )
    @CredentialsAnnotation()
    private String password;


    //TODO - for each property we add we should have getter method for that property.
    // It is recommended to use IntelliJ's getter generator to generate getter methods after adding the properties
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    //TODO - FYI - this class extends ConnectionData class from base library
    // ConnectionData class has below properties
    //  -  entities  : this property is a list of entity configuration thats needed at the time of connection creation from UI. It's an optional field.
    //  -  blacklist : this property is not an user input field. But if we want to blacklist any entities from fetching, we can add that property to this list.
    //  -  misc      : this is an object of miscellaneous fields which are not necessarily taken from UI but those would help to design and develop the project better.

//    misc properties example :
//    Third party integration like salesforce, which provides few different ways to fetch the data.
//    It has both batch api and rest api. Its recommended to use the batch api to fetch large set of data
//    in case, if we run out of any batch api usage limit for the day, we might have to wait for next day to fetch the data.
//    In such case, we can fetch data using rest api. We can set a field/flag that tells which api mechanism to use to fetch data.
//    Those kind of fields should go into misc properties.
//    These kind of properties has to be updated in the connection and then we can retrieve them as shown below

//    public boolean isBulkApiEnabled() {
//        String isBulkApiEnabled = getMiscPropertyByName("bulkApiEnabled");
//        return Boolean.parseBoolean(isBulkApiEnabled);
//    }


}
