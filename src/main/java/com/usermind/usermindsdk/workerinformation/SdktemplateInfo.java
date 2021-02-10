package com.usermind.usermindsdk.workerinformation;

import com.usermind.usermindsdk.authentication.oauth.OAuthConfig;
import com.usermind.usermindsdk.registration.Environment;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SdktemplateInfo implements WorkerInfo {
    private static final String WORKER_TYPE = "sdktemplate";
    private static final String DISPLAY_NAME = "Sdktemplate";
    private static final String WORKER_VERSION = "1.0";

    @Override
    public String getWorkerType() {
        return WORKER_TYPE;
    }

    @Override
    public String getWorkerVersion() {
        return WORKER_VERSION;
    }


    @Override
    public AuthenticationType getAuthenticationType() {
        //TODO -  This is to provide information on what authentication mechanism this integration uses
        // If we need to make a connection through OAuth , then we can call it as
        // return AuthenticationType.OAUTH;
        // else
        // return AuthenticationType.KEYVALUE;
        return AuthenticationType.KEYVALUE;
    }

    @Override
    public String getWorkerDisplayName() {
        //TODO - Display Name of an integration. This will be shown on the UI while listing this integration
        return DISPLAY_NAME;
    }

    @Override
    public String getWorkerCategory() {
        //TODO - This is just to tell which category this integration belongs to.
        // For ex - facebook integration can be put under social networking category
        return "Misc";
    }


    @Override
    public boolean isConcurrent() {
        //Once in a while there is an integration that can't allow multiple threads to hit it.
        //If that is the case, then change this to false and only one thread will handle all actions
        //for this integration type for a customer.
        return true;
    }

    /* TODO -
     * If this SDK implements AuthenticationType.OAUTH then keep this method else remove it.
     * Third party integrations may have different environments like sandbox, test or production.
     * this method will help register the number of environments that this SDK will support to Front End.
     */
    @Override
    public List<Environment> getEnvironments() {
        List<Environment> environments = new ArrayList<>();
        //PROD Instance
        //Use Environment.getDefaultInstance(); to create a prod environment.
        Environment prodEnvironment = Environment.getDefaultInstance();
        OAuthConfig prodConfig = new OAuthConfig(
                "Authorization URI HERE",
                "Access Token URI HERE");

        //By Default,  AuthenticationScheme of type form is configured in the OAuthConfig
        //Many integrations uses form AuthenticationScheme.
        //If we want to change it to something else, then set the appropriate scheme here! Otherwise remove it
        prodConfig.setAuthenticationScheme(AuthenticationScheme.header);

        prodEnvironment.setOauthConfig(prodConfig);

        //Eg - Sandbox Instance
        Environment sandBoxEnvironment = new Environment();
        OAuthConfig sandboxConfig = new OAuthConfig(
                "",
                "");
        //By Default,  AuthenticationScheme of type "form" is configured in the OAuthConfig
        //Many integrations uses form AuthenticationScheme.
        //If we want to change it to something else, then set the appropriate scheme here. Otherwise remove it
        sandboxConfig.setAuthenticationScheme(AuthenticationScheme.query);

        sandBoxEnvironment.setId("sandbox");
        sandBoxEnvironment.setDisplayName("sandbox");
        sandBoxEnvironment.setIconsAvailable(true);
        sandBoxEnvironment.setOauthConfig(sandboxConfig);

        environments.add(prodEnvironment);
        environments.add(sandBoxEnvironment);
        return environments;
    }
}
