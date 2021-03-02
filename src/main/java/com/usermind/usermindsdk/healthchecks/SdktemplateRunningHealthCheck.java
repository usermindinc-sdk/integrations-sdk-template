package com.usermind.usermindsdk.healthchecks;

import org.springframework.stereotype.Component;

@Component
public class SdktemplateRunningHealthCheck extends HealthCheck {

    @Override
    public Result check() throws Exception {
        return Result.healthy();
    }
}
