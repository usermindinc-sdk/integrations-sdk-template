package com.usermind.usermindsdk.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.exceptions.InvalidSessionException;

import java.time.OffsetDateTime;

/*
This class is to store session credentials. It is very similar to Credential Container. The base class stores a JSON node, you can just write get methods for
any things in particular that you need from the session object.

Some connections have credentials, that when used will return a temporary session. This is to store that session information.
The notable thing is that the session will expire - so when you get the session, you need to call setExpirationTime. Pass in
the OffsetDateTime that the session will expire, or else a localtime and the timezone to use.

15 minutes before that time happens, this will start returning "true" if you call "shouldRenew", telling you to renew the session.
If you use the templated manager class, then it will renew the session automatically when one is requested.

If you want to change the 15 minute interval, just call setRenewalTimePeriodMinutes.

 */
public class SessionCredentialContainerSdktemplate extends SessionCredentialContainer {

    public SessionCredentialContainerSdktemplate(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public void load(JsonNode jsonNode) throws Exception {
//Sample code - it gets a node, extracts the part of the node with session information, and stores that as the root node.
//It should have expiration information somewhere. This needs to also parse out when the session expires, and call
//setExpirationTime with that.

//        JsonNode dataNode = null;
//        JsonNode data = jsonNode.get("data");
//        if (data != null && data.size() > 0) {
//            dataNode = data.get(0);
//        }
//        if (dataNode == null) {
//            throw new InvalidSessionException("There was not valid session information in the returned session object.");
//        }
//
//        saveRootNode(dataNode);
//
//        setExpirationTime(getExpiresOn());
    }


// And then some helper get methods, so that you can have the compiler help enforce calling the right things.
// Mainly note the getExpiresOn call. That gets out the time when the session expires and converts it to an OffsetDateTime.
// You may have to convert it to something different based on what you're sent, but this should give you a working example
// at least.

//    public String getToken() {
//        return getFromRoot("Token");
//    }
//
//    public String getSecret() {
//        return getFromRoot("Secret");
//    }
//
//    public OffsetDateTime getExpiresOn() throws JsonProcessingException {
//        JsonNode timeNode = rootNode.get("ExpiresOn");
//        OffsetDateTime tempTime = objectMapper.treeToValue(timeNode, OffsetDateTime.class);
//        return tempTime;
//    }

//Something like this just helps when seeing things in the debugger
//    @Override
//    public String toString(){
//        return
//                "SessionInformation{" +
//                        "access_token = '" + getAccessToken() + '\'' +
//                        ",expires_in = '" + getExpiresIn() + '\'' +
//                        "}";
//    }

}
