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

}
