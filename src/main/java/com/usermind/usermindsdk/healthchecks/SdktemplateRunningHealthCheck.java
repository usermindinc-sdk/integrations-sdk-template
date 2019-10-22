package com.usermind.usermindsdk.healthchecks;

import com.codahale.metrics.health.HealthCheck;

/*
    TODO: nothing to change in this class, just leave as is!!!
 */

public class SdktemplateRunningHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
