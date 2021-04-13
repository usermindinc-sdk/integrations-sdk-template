package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.actions.actionreturn.ActionResults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
    TODO: Make sure to change the names of this and all the other classes in this folder according
        to the type of Action you are performing instead of the generic CreateEntity
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateCreateEntityActionIT extends TestBase {

    private Map<String, SdktemplateCreateEntityInput> getInput() {
        SdktemplateCreateEntityInput inputData = new SdktemplateCreateEntityInput();
        String uuid = UUID.randomUUID().toString();
        inputData.setField("id", "id_" + uuid);
        inputData.setField("name", "name_" + uuid);
        inputData.setField("data", "field" + uuid);

        Map<String, SdktemplateCreateEntityInput> inputMap = new HashMap<>();
        inputMap.put("key", inputData);
        return inputMap;
    }


    @Test
    void testCreateAction() throws Exception {
        ActionHandler actionHandler = ctx.getBean(ActionHandler.class);

        String entityName = "entity name here";
        ActionResults actionResults = actionHandler.runAction(TestClassFactory.getCredentialContainerSdktemplate(),
                entityName, getInput());

        return;
    }
}
