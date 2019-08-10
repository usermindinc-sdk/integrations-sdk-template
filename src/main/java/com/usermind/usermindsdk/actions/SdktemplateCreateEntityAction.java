package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("CreateEntity")
public class SdktemplateCreateEntityAction implements ActionHandler<SdktemplateConnectionData, SdktemplateCreateEntityInput>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCreateEntityAction.class);

    @Override
    public void runAction(SdktemplateConnectionData sdktemplateConnectionData, String s, SdktemplateCreateEntityInput sdktemplateCreateEntityInput) throws Exception {

        //Throw new SDKActionFailedException if the action fails.

        //This goes with SdktemplateCreateEntityInput - they are a pair, with the same names except that one ends in Action and one ends in Input.
    }
}
