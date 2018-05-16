package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.fetch.samplefetch.SampleData;
import com.usermind.usermindsdk.fetch.samplefetch.SampleFetch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/v1/samplefetch")
@Api(value = "Sample Fetch API")
@Produces(MediaType.APPLICATION_JSON)
public class SampleFetchURLHandler {

    private SampleFetch sampleFetch;

    @Autowired
    public SampleFetchURLHandler(SampleFetch sampleFetch) {
        this.sampleFetch = sampleFetch;
    }

    /*
    {
  "connectionData": {
    "encrypted": {
      "credentials": {
        "clientId": "ragi-test",
        "clientSecret": "nM_bPyV4sfbVBz8Po28g"
      },
      "endpoints": {
        "api": "unused",
        "identity": "unused"
      }
    }
  }
}
     */
    @POST
    @ApiOperation(value = "Perform Sample Fetch",
            notes = "Run a sample fetch for a customer to return some example data.",
            tags = {"API", "Fetch"})
    public SampleData runSampleFetch() throws NoSuchMethodException {
        return sampleFetch.runSampleFetch();
    }

}
