package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class AddAuthenticationInformationSdktemplate implements AddAuthenticationInformation<SdktemplateConnectionData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAuthenticationInformationSdktemplate.class);

    /*
    TODO - If you have sessions, uncomment this block to get the session manager. If you don't have sessions,
        delete this block.

    private final SdktemplateSessionManager sessionManager;

    @Autowired
    public AddAuthenticationInformationSdktemplate(SdktemplateSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
*/
    @Override
    public String putAuthIntoWebRequest(SdktemplateConnectionData sdktemplateConnectionData, String link, Map<String, String> headers)throws Exception {
        //TODO - put authentication information into the headers
        //Some integrations add them to the link directly instead of the headers. The link returned is therefore the link used, NOT the
        //one sent in - this lets you add authentication to that as well.

        //TODO - If you have sessions, then uncomment this to get the session information:
        //SdktemplateSession session = sessionManager.getSession(sdktemplateConnectionData);


   /*
    EXAMPLES
    Here are 2 examples of a complete implementation of this method taken from other integrations

    Example 1:

        Add some session information to the url as a query parameter:
        UriBuilder uriBuilder = UriBuilder
                .fromPath(link)
                .queryParam("access_token", sessionInformation.getAccessToken());
        link = uriBuilder.toString();
        return link;

    Example 2:

        Add authentication information to the headers, return the same link:
        headers.put(AUTHORIZATION, TOKEN_STRING + credentials.getToken());
        return link;

    */
        checkNotNull(sdktemplateConnectionData);
        checkNotNull(headers);
        return link;
    }

}
