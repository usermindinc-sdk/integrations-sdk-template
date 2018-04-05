package com.usermind.usermindsdk.dropwizard.urlhandlers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
@Api(value = "Root Page Handler")
@Produces(MediaType.TEXT_HTML)
public class RootURLHandler {

    @Autowired
    private RootView rootView;

    public RootURLHandler() {
        //Empty Constructors are sometimes required by Spring ...
    }

    @GET
    @ApiOperation(value = "Worker Home Page",
            notes = "Useful for displaying worker information.",
            tags = "internal")
    public RootView showRootPage() {
        return rootView;
    }

}
