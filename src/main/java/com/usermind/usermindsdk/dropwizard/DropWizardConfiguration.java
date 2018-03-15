package com.usermind.usermindsdk.dropwizard;

import io.dropwizard.Configuration;

public class DropWizardConfiguration extends Configuration {

    private WorkerConfiguration workerConfiguration = new WorkerConfiguration();

    public WorkerConfiguration getWorkerConfiguration() {
        return workerConfiguration;
    }
}
