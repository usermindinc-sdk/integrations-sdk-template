package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.actions.actionreturn.ActionResults;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateEntity;
import com.usermind.usermindsdk.exceptions.SDKActionConfigurationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
    TODO: Make sure to change the names of this and all the other classes in this folder according
        to the type of Action you are performing instead of the generic CreateEntity
 */

/*
   * TODO: We need to put the action config section into IntegrationConfiguration.json file which is located at src/main/resources/com/usermind/usermindsdk/registration/
   * It's an very important step as Front end looks at the above mentioned config file for action metadata.
   * If we have implemented action but haven't configured in the above json file, then the action won't be visible for the users.
   *
   * We want to use the standard action format as mentioned in this doc- https://usermind.atlassian.net/wiki/spaces/LOH/pages/1752137787/Standard+Action+Config+for+SDK
   * As per this standard, all the action inputs which needs to be shown on the UI are coming from the Entity Metadata.
   * So we need to create action specific entities in the metadata section of this SDK.
   * - For More details go to - MetadataFetchSetupSdktemplate.java and look for "SUPPORT METHOD FOR 1.a - Write action entity directly" section
   *
 */
@Component("CreateEntity") /* TODO: This is the action name. Change the action name as desired */
public class SdktemplateCreateEntityAction implements ActionHandler<SdktemplateConnectionData, SdktemplateCreateEntityInput> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SdktemplateCreateEntityAction.class);


    @Override
    public int getBatchSize() {
        //If actions go out one at a time, leave this as 1. But if you can get a set of actions and combine them into a single call to the client,
        //then make this number the maximum number of actions you want to be sent at a time.
        //The runAction call below takes a collection. That collection will be no larger than the number specified here. If you specify 1, actions
        //will not be batched, and you'll get a single item. If you specify 100, you'll get up to 100 actions in the collection that you can group together.
        return 1;
    }

    @Override
    public Set<String> getGroupingFields() {

        //If the batch size is 1, then this is ignored. But if the action size is larger than one, then you can optionally specify a field to take into account when grouping
        //actions.
        // For example, say the input has fields Name, state, and ID. You can group things together but only by state. So then you would return:
        //return new HashSet<>(Arrays.asList("state"));
        //The orchestration code will then check the fields and look for state. It will aggregate the actions by state, and send you batches in which all the states have the same value.
        //The field not being found or being empty are treated as their own identical case and will also be batched together.
        return new HashSet<>();
    }

    @Override
    public ActionResults runAction(SdktemplateConnectionData connectionData, String entityName, Map<String, SdktemplateCreateEntityInput> actions) throws Exception {

        ActionResults results = new ActionResults();

        //Find the entity in the connection -- if you don't need to find anything from the entity,
        //you can remove this (and the matching unit test.)
        for (Map.Entry<String, SdktemplateCreateEntityInput> actionEntry : actions.entrySet()) {
            SdktemplateEntity sdktemplateEntity = connectionData
                    .getEntities()
                    .stream()
                    .filter(e -> e.getEntityName().equals(entityName))
                    .findAny()
                    .orElse(null);

            if (sdktemplateEntity == null) {
                throw new SDKActionConfigurationFailedException("Entity '" + entityName + "' was not defined in the connection.");
            }

            //TODO: Do the work here ...
        }

        //Throw new SDKActionFailedException if the action fails completely. That means no call succeeded, and there is no
        //return information from the API.

        //ONLY CATCH ERRORS SPECIFIC TO THE INTEGRATION THAT YOU CAN ADD CONTEXT TO
        //Rethrow anything you don't specifically handle. A lot of things, such as socket exceptions,
        //come in and can be retried - but it is very easy to accidentally catch them and swallow them,
        //and then they won't be retried.

        //If you do want to catch everything - then add this block to your catch right before catching the general Exception:
//        catch (RestClientException e) {
//            throw e;
//        }
        //Those exceptions are handled by the higher level code in a generic fashion, which is why it's better to rethrow them.


        //This goes with SdktemplateCreateEntityInput - they are a pair, with the same names except that one ends in Action and one ends in Input.

        /*
        What gets passed in is a group actions, of any size up to the specified BatchSize (if this integration can't batch actions, specify a batch size of 1.)

        The actions are in a map. The key is an identifier, the value is the action. It is up to the developer to group the actions as appropriate for batching, and then
        to run them.

        Return a list of actions that failed. The key is the same identifier as the input map.
        If an action succeeded, do nothing - but for each action that failed, return the ID of the failed action and an ActionFailureResult.
        ActionFailureResult is string with an error message and a boolean.
        A blank error message is NOT acceptable - return something detailing what went wrong, as clearly as possible. This is what will be seen when we look to
        figure out why the action failed.
        The boolean is to report if the action can be retried or not. For most failures - let it retry. But if the failure is something returned by the API
        that tells you that retrying will not help, then return a false and it will not be retried.

        It is up to the developer to take the return call from the integration and figure out which actions failed. if this is impossible, then fail the entire batch of actions
        with an appropriate error message.

        If this is NOT a batched action - ie, getBatchSize returns 1 - then you can also just throw an exception. That will get caught by the caller which will then assume each
        action failed. For a batch size of one, that is an appropriate thing.

         */

        return results;

    }
}
