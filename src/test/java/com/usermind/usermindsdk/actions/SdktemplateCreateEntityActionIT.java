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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateCreateEntityActionIT extends TestBase {

    @Test
    void testCreateAction() throws Exception {
        ActionHandler actionHandler = ctx.getBean(ActionHandler.class);

        String entityName = "entity name here";
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> rowData = new HashMap<>();
        rowData.put("id", "id_" + uuid);
        rowData.put("name", "name_" + uuid);
        rowData.put("data", "field" + uuid);


        actionHandler.runAction(TestClassFactory.getCredentialContainerSdktemplate(),
                entityName, rowData);

        return;
    }
}
