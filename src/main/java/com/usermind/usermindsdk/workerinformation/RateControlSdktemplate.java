package com.usermind.usermindsdk.workerinformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class RateControlSdktemplate implements  RateControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateControlSdktemplate.class);

    private Integer maxRate = 100000;
    private Integer TimeframeMinutes = 1;

    @Override
    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        maxRate = checkNotNull(maxRate);
    }

    @Override
    public Integer getTimeframeMinutes() {
        return TimeframeMinutes;
    }

    public void setTimeframeMinutes(Integer timeframeMinutes) {
        TimeframeMinutes = checkNotNull(timeframeMinutes);
    }
}
