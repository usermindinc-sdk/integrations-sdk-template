package com.usermind.usermindsdk.healthchecks;

import com.codahale.metrics.health.HealthCheck;

public class SdktemplateRunningHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}