package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/v1/fullfetch")
@Api(value = "Full Fetch API")
@Produces(MediaType.APPLICATION_JSON)
public class FullFetchURLHandler {

    private FullFetch fullFetch;

    @Autowired
    public FullFetchURLHandler(FullFetch fullFetch) {
        this.fullFetch = fullFetch;
    }

    @POST
    @ApiOperation(value = "Perform a full fetch",
            notes = "Run a full fetch against a worker, retrieve all data, and write it to S3.",
            tags = {"API", "Fetch"})
    public void runFullFetch() {
        //TODO - somewhere in here this needs to get threaded off and managed
        fullFetch.runFullFetch();

        //Needs to know -
        //organization id
        //legacy connection id
        //run id
    }

}
