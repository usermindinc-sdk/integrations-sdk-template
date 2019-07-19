package com.usermind.usermindsdk.authentication.credentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SdktemplateConnectionData extends ConnectionData<SdktemplateEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateConnectionData.class);

    //TODO - add a field for each credential item
    @UserInputAnnotation(displayName = "Username", toolTip = "A username")
    @CredentialsAnnotation()
    private String username;

    @UserInputAnnotation(displayName = "Password", toolTip = "The password")
    @CredentialsAnnotation()
    private String password;


}
