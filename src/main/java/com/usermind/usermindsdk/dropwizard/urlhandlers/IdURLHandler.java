package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/id")
@Api(value = "ID API")
@Produces(MediaType.APPLICATION_JSON)
public class IdURLHandler {

    private WorkerInfo workerInfo;

    @Autowired
    public IdURLHandler(WorkerInfo workerInfo) {
        this.workerInfo = workerInfo;
    }

    @GET
    @ApiOperation(value = "Get identification information",
            notes = "Get basic information in JSON format to identify this worker.",
            tags = "informational")
    public WorkerInfo showRootPage() {
        return workerInfo;
    }

}
