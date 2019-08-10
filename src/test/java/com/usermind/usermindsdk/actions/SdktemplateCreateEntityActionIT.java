package com.usermind.usermindsdk.actions;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateCreateEntityActionIT extends TestBase {

    private SdktemplateCreateEntityInput getInput() {
        SdktemplateCreateEntityInput inputData = new SdktemplateCreateEntityInput();
        String uuid = UUID.randomUUID().toString();
        inputData.setField("id", "id_" + uuid);
        inputData.setField("name", "name_" + uuid);
        inputData.setField("data", "field" + uuid);

        return inputData;
    }


    @Test
    void testCreateAction() throws Exception {
        ActionHandler actionHandler = ctx.getBean(ActionHandler.class);

        String entityName = "entity name here";
        SdktemplateCreateEntityInput inputData = getInput();
        actionHandler.runAction(TestClassFactory.getCredentialContainerSdktemplate(),
                entityName, inputData);

        return;
    }
}
