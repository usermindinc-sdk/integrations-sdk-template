package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.fetch.samplefetch.SampleData;
import com.usermind.usermindsdk.fetch.time.TimeLimitedFetch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/v1/timefetch")
@Api(value = "Time Limited Fetch API")
@Produces(MediaType.APPLICATION_JSON)
public class TimeFetchURLHandler {

    TimeLimitedFetch timeLimitedFetch;

    @Autowired
    public TimeFetchURLHandler(TimeLimitedFetch timeLimitedFetch) {
        this.timeLimitedFetch = timeLimitedFetch;
    }

    @POST
    @ApiOperation(value = "Perform Time Based Fetch",
            notes = "Run a time limited fetch for a customer to return changes made in that timeframe.",
            tags = {"API", "Fetch"})
    public SampleData runTimeFetch(@FormParam("startTime") Long startTime, @FormParam("endTime") Long endTime) throws NoSuchMethodException {
        return timeLimitedFetch.performTimeLimitedFetch();
    }

}
