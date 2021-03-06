package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Example;

import java.time.OffsetDateTime;

/*
This class is to store session credentials. It is very similar to Connection Data in some ways.
The base class stores a JSON node, you can just write get methods for
any things in particular that you need from the session object.
 */
public class SdktemplateSession extends ConnectionSession {

    public SdktemplateSession() {
    }

    //  Example code:
    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private OffsetDateTime expires_in;

    @JsonCreator
    public SdktemplateSession(@JsonProperty("access_token") String accessToken, @JsonProperty("expires_in") OffsetDateTime expires_in) {
        this.accessToken = accessToken;
        this.expires_in = expires_in;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public OffsetDateTime getExpiresIn() {
        return expires_in;
    }




}
