package com.usermind.usermindsdk.dropwizard.urlhandlers.json;

import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import org.springframework.stereotype.Component;

@Component
public class TitoInfo implements WorkerInfo {
    private static final String WORKER_TYPE = "Tito";
    private static final String WORKER_VERSION = "2.0";

    @Override
    public String getWorkerType() {
        return WORKER_TYPE;
    }

    @Override
    public String getWorkerVersion() {
        return WORKER_VERSION;
    }

}
