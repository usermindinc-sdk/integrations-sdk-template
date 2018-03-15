package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.dropwizard.urlhandlers.json.WorkerInfo;
import com.usermind.usermindsdk.fetch.FullFetch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/fullfetch")
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
            tags = "API")
    public void showRootPage() {
        fullFetch.runFullFetch();

        //Needs to know -
        //organization id
        //legacy connection id
        //run id
    }

}
