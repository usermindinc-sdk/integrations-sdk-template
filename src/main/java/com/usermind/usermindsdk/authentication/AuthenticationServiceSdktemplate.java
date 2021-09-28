package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateSessionManager;
import com.usermind.usermindsdk.authentication.exceptions.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/*TODO -
 * This class should be used for integration which uses Authorization type: Key Value Pair
 * if we are using this class then delete the class - AuthenticationServiceSdktemplate1
 * Idea is , we need to have only one class which extends AuthenticationService class
 */
@Component
public class AuthenticationServiceSdktemplate implements AuthenticationService<SdktemplateConnectionData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceSdktemplate.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AddAuthenticationInformationSdktemplate addAuthInfo;

    private final SdktemplateSessionManager sessionCredentialManager;
    //Use this variable to fill in the URL of the path to authenticate - then the unit tests will work. If you change
    //this, just change the tests to match.
    public static final String AUTH_CHECKING_PATH = "https://example.com/authentication";

    @Autowired
    public AuthenticationServiceSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper,
                                            AddAuthenticationInformationSdktemplate addAuthInfo,
                                            SdktemplateSessionManager sessionManager) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.addAuthInfo = addAuthInfo;
        this.sessionCredentialManager = sessionManager;
    }

    public AuthenticatorResponse validate(SdktemplateConnectionData connectionData) throws Exception {
        LOGGER.info("Sdktemplate authentication started.");
        //TODO - implement Authenticator
        //  If there is a session - this call is all you need here to validate. But ideally you'll then
        //  get an entity list.
        //SdktemplateSession session = SdktemplateSessionManager.getSession(sdktemplateConnectionData);
        //TODO - If there is not a session, then just make a rest call using the credentials and see if it succeeds or not.
        //  And ideally make that call one that gets a list of available entities.
        //List<String> entities;

        HttpHeaders headers = new HttpHeaders();
        addAuthInfo.putAuthIntoWebRequest(connectionData, "", headers);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(AUTH_CHECKING_PATH, HttpMethod.GET, entity, String.class);
        } catch (HttpClientErrorException ex) {
            throw new InvalidCredentialsException(ex.getMessage() + " -- " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            throw new InvalidCredentialsException(ex.getMessage());
        }
        //Fill in entities with the list of everything we have access to read.
        //return AuthenticatorResponse.success(ENTITIES);
        //or
        //return AuthenticatorResponse.failure(Status.CREDENTIALS_FAILURE,"explanation string");

        throw new NoSuchMethodException("Authentication has not been implemented for this integration type.");
    }

}
