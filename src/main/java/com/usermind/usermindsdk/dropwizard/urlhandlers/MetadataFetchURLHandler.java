package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.entities.Input;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import com.usermind.usermindsdk.fetch.samplefetch.SampleData;
import com.usermind.usermindsdk.fetch.samplefetch.SampleFetch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Component
@Path("/v1/metadatafetch")
@Api(value = "Metadata Fetch API")
@Produces(MediaType.APPLICATION_JSON)
public class MetadataFetchURLHandler {

    private MetadataFetch metadataFetch;
    private final ObjectMapper objectMapper;

    @Autowired
    public MetadataFetchURLHandler(MetadataFetch metadataFetch, ObjectMapper objectMapper) {
        this.metadataFetch = metadataFetch;
        this.objectMapper = objectMapper;
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
    @ApiOperation(value = "Perform Metadata Fetch",
            notes = "Run a metadata fetch for a customer.",
            tags = {"API", "Fetch"})
    public Events runMetaDataFetch(@FormParam("connectionData") String connectionDataStr) throws IOException, NoSuchMethodException {
        Input input = objectMapper.readValue(connectionDataStr, Input.class);
        return metadataFetch.runMetadataFetch(input.getConnectionData().getEncrypted().getCredentials().getClientId(),
                input.getConnectionData().getEncrypted().getCredentials().getClientSecret());
    }

}
