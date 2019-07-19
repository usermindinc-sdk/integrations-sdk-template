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
        return AuthenticationType.KEYVALUE;
    }

    @Override
    public String getWorkerDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getWorkerCategory() {
        return "Misc";
    }

}
