package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CreateEntity")
public class SdktemplateCreateEntityAction implements ActionHandler<SdktemplateConnectionData, SdktemplateCreateEntityInput>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCreateEntityAction.class);


    @Override
    public int getBatchSize() {
        return 1;
    }

    @Override
    public Map<String, String> runAction(SdktemplateConnectionData connectionData, String entityName, Map<String, SdktemplateCreateEntityInput> actions) throws Exception {

        Map<String, String> failedActions = new HashMap<>();
        //Throw new SDKActionFailedException if the action fails.

        //This goes with SdktemplateCreateEntityInput - they are a pair, with the same names except that one ends in Action and one ends in Input.

        /*
        What gets passed in is a group actions, of any size up to the specified BatchSize (if this integration can't batch actions, specify a batch size of 1.)

        The actions are in a map. The key is an identifier, the value is the action. It is up to the developer to group the actions as appropriate for batching, and then
        to run them.

        Return a list of actions that failed. If an action succeeded, do nothing - but for each action that failed, return the ID of the failed action and a string
        with an error message. A blank error message is not acceptable - return something detailing what went wrong, as clearly as possible.

        It is up to the developer to take the return call from the integration and figure out which actions failed. if this is impossible, then fail the entire batch of actions
        with an appropriate error message.

         */

        return failedActions;
    }
}
