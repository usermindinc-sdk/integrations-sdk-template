package com.usermind.usermindsdk.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppRunningHealthCheck extends HealthCheck {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunningHealthCheck.class);

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
