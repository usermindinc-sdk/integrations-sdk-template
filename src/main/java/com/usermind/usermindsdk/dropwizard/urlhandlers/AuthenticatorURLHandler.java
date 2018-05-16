package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.authentication.Authenticator;
import com.usermind.usermindsdk.authentication.entities.Input;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchData;
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
@Path("/v1/authenticate")
@Api(value = "Authenticator API")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticatorURLHandler {

    private Authenticator authenticator;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthenticatorURLHandler(Authenticator authenticator, ObjectMapper objectMapper) {
        this.authenticator = authenticator;
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

SHOULD BE:
{accountName: "name", token: "key"}
     */
    @POST
    @ApiOperation(value = "Authenticate credentials",
            notes = "Run a metadata fetch against a worker to test out the supplied credentials.",
            tags = "API")
    public MetadataFetchData authenticate(@FormParam("connectionData") String connectionDataStr) throws IOException, NoSuchMethodException {
        Input input = objectMapper.readValue(connectionDataStr, Input.class);
        return authenticator.performAuthentication(input.getConnectionData().getEncrypted().getCredentials().getClientId(),
                input.getConnectionData().getEncrypted().getCredentials().getClientSecret());
    }

}
