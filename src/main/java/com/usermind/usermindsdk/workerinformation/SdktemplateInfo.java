package com.usermind.usermindsdk.workerinformation;

import org.springframework.stereotype.Component;

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
}
