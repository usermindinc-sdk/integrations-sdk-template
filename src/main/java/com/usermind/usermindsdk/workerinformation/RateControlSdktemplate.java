package com.usermind.usermindsdk.workerinformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;
/*
    TODO: nothing to change in this class, just leave as is!!!
 */

@Component
public class RateControlSdktemplate implements  RateControl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateControlSdktemplate.class);

    private Integer maxRate = 100000;
    private Integer timeframeMinutes = 1;

    @Override
    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = checkNotNull(maxRate);
    }

    @Override
    public Integer getTimeframeMinutes() {
        return timeframeMinutes;
    }

    public void setTimeframeMinutes(Integer timeframeMinutes) {
        this.timeframeMinutes = checkNotNull(timeframeMinutes);
    }
}
