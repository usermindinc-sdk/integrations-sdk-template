package com.usermind.usermindsdk.workerinformation;

import org.springframework.stereotype.Component;

@Component
public class SdktemplateInfo implements WorkerInfo {
    private static final String WORKER_TYPE = "Sdktemplate";
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
        // If we need to make a connection through OAuth , then we can all it as
        // return AuthenticationType.OAUTH
        // else
        // return AuthenticationType.KEYVALUE
        return null;
    }

    @Override
    public String getWorkerDisplayName() {
        //TODO - Display Name of an integration. This will be shown on the UI while lisitng this integration
        return "";
    }

    @Override
    public String getWorkerCategory() {
        //TODO - This is just to tell which category this integration belongs to.
        // For ex - facebook integration can be put under social networking category
        return "";
    }
}
