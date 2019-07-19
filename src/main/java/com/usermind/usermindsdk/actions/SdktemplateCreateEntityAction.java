package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("CreateEntity")
public class SdktemplateCreateEntityAction implements ActionHandler<SdktemplateConnectionData>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCreateEntityAction.class);

    @Override
    public void runAction(SdktemplateConnectionData sdktemplateConnectionData, String s, Map<String, Object> map) throws Exception {

        //Throw new SDKActionFailedException if the action fails.
    }
}
