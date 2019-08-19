package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        actionHandler.runAction(TestClassFactory.getCredentialContainerSdktemplate(),
                entityName, getInput());

        return;
    }
}
