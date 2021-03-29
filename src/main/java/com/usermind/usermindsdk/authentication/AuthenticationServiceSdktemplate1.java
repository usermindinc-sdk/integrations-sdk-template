package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData1;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateSessionManager;
import com.usermind.usermindsdk.authentication.oauth.OAuthService;
import com.usermind.usermindsdk.spring.WorkerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/*TODO -
 * This class should be used for integration which uses Authorization type: OAuth - Authorization Code grant
 * if we are using this class then delete the class - AuthenticationServiceSdktemplate
 * and rename this class to AuthenticationServiceSdktemplate
 * Idea is , we need to have only one class which extends AuthenticationService class
 */
@Component
public class AuthenticationServiceSdktemplate1 extends OAuthService<SdktemplateConnectionData1> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceSdktemplate1.class);

    private final SdktemplateSessionManager sessionCredentialManager;
    //Use this variable to fill in the URL of the path to authenticate - then the unit tests will work. If you change
    //this, just change the tests to match.
    public static final String AUTH_CHECKING_PATH = "https://example.com/authentication";

    @Autowired
    public AuthenticationServiceSdktemplate1(SdktemplateSessionManager sessionManager,
                                             WorkerConfiguration workerConfiguration) {
        super(workerConfiguration);
        this.sessionCredentialManager = sessionManager;
    }

    /*
     * These methods are already handled in the base class. You can override them if desired, otherwise delete this.
     */
    @Override
    public String initiateOAuthRequest(SdktemplateConnectionData1 connectionData, String environment) throws Exception {
        return super.initiateOAuthRequest(connectionData, environment);
    }

    /*
     * These methods are already handled in the base class. You can override them if desired, otherwise delete this.
     */
    @Override
    public OAuth2AccessToken grantCode(SdktemplateConnectionData1 connectionData, String code, String environment) throws Exception {
        return super.grantCode(connectionData, code, environment);
    }

    /*
     * These methods are already handled in the base class. You can override them if desired, otherwise delete this.
     */
    @Override
    public AuthorizationCodeAccessTokenProvider getAccessTokenProvider() {
        return super.getAccessTokenProvider();
    }
}
