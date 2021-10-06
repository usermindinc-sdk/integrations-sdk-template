package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.usermind.usermindsdk.authentication.oauth.OAuthConfig;
import com.usermind.usermindsdk.registration.Environment;
import com.usermind.usermindsdk.registration.Environments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Component;

/*TODO -
 * This class should be used for integration which uses Authorization type: OAuth - Authorization Code grant
 * if we are using this class then delete the class - SdktemplateConnectionData
 * and rename this class to SdktemplateConnectionData
 * Idea is , we need to have only one class which extends ConnectionData class
 */

/* TODO -
 * Third party integrations may have different environments like sandbox, test or production.
 * this method will help register the number of environments that this SDK will support to Front End.
 */
@Environments({
        @Environment(
                oauthConfig = @OAuthConfig(
                        userAuthorizationUri = "Authorization URI HERE",
                        accessTokenUri = "Access Token URI HERE",
                        scopes = {
                                "chat:write:bot",
                                "channels:read",
                                "groups:read",
                                "users:read"}
                                )),

        @Environment(
                id = "sandbox",
                displayName = "Sandbox",
                oauthConfig = @OAuthConfig(
                        userAuthorizationUri = "Authorization URI HERE",
                        accessTokenUri = "Access Token URI HERE",
                        authenticationScheme = AuthenticationScheme.header
                ))
})
@Component
@Scope("prototype")
public class SdktemplateConnectionData extends OAuthConnectionData<SdktemplateEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateConnectionData.class);

    /* TODO - add properties of the connection with appropriate annotations -
    * if we need username and password to make a http call to get any required data, then add both the properties here.
    * There are two annotations available
       1. @UserInputAnnotation - if we want any properties to be taken from UI.
         - This annotation has three properties
         - a. Display Name -  some user friendly name which will be shown on the UI input box
         - b. hidden -  this field is used to hide the user input entires on the UI. By default its set to false.
                        Example, anything we type for password entry will be shown as hidden values
         - c. toolTip -  this is to give elaborated detail about the field. If we want to give a sample input entry for the given key, we can mention here.

       2. @CredentialsAnnotation - this field is used to tell system to save the respective keys with encryption in our system.
                                 - also its possible that some of the keys are computed ones (ex : accessToken) and which are not taken from UI.
                                 - for such fields we can add this annotations.
    */
    @UserInputAnnotation(displayName = "Client Id",
            toolTip = "Client Id"
    )
    @CredentialsAnnotation()
    private String clientId = "";

    @UserInputAnnotation(displayName = "Client Secret",
            hidden = true,                             //<----- If its set to true, whatever the value we enter on the UI will not be visible as plain text
            toolTip = "Client Secret"
    )
    @CredentialsAnnotation()
    private String clientSecret = "";

    @CredentialsAnnotation()
    private String accessToken = "";

    @CredentialsAnnotation()
    private String refreshToken = "";


    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    //TODO - for each property we add we should have getter method for that property.
    // It is recommended to use IntelliJ's getter generator to generate getter methods after adding the properties
    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }


    /*TODO -
        * When credentials are generated after the OAuth Grant call, we need to parse the response
        * and assign the values from the response to this class variables.
        * This method is used by SDK base to save the credentials to IRA database after all oauth calls are complete.
        * For ex - Salesforce response after OAuth Grant call looks like this.
        *   {
                  "access_token": "access_token value",
                  "token_type": "Bearer",
                  "refresh_token": "refresh_token value",
                  "scope": "api refresh_token",
                  "signature": "my0KScjCFUJU6L24ja3+lp4vokoEWbKsIhzS3fKvq6k=",
                  "instance_url": "https://na172.salesforce.com",
                  "id": "https://login.salesforce.com/id/00D5w00000dasdaEA2/0dasda00CTHCYAA5",
                  "issued_at": "1608119113122"
             }
         * We would want to parse this response and assign the values to appropriate properties.
     */
    @Override
    public OAuthConnectionData parseAccessToken(ObjectNode tokenResponse) {
        this.accessToken = tokenResponse.get("access_token").asText();
        this.refreshToken = tokenResponse.get("refresh_token").asText();
        return this;
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
