package com.usermind.usermindsdk.dropwizard.urlhandlers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @POST
    @ApiOperation(value = "Perform Time Based Fetch",
            notes = "Run a time limited fetch for a customer to return changes made in that timeframe.",
            tags = {"API", "Fetch"})
    public void runSampleFetch(@FormParam("startTime") Long startTime, @FormParam("endTime") Long endTime) throws NoSuchMethodException {
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }

}
