package com.usermind.usermindsdk.actions;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateCreateEntityActionTest extends TestBase {

    private SdktemplateCreateEntityAction actionHandler;

    @BeforeEach
    void setUp() throws Exception {
        actionHandler = new SdktemplateCreateEntityAction();
    }

    private Map<String, Object> getRowData() {
        Map<String, Object> rowData = new HashMap<>();
        rowData.put("keyOne", "valueOne");
        rowData.put("keyTwo", "valueTwo");
        rowData.put("keyThree", "valueThree");

        return rowData;
    }

    @Test
    void testValid() throws Exception {
        String entityName = "Products";
        Map<String, Object> rowData = getRowData();
        SdktemplateConnectionData connectionData = TestClassFactory.getCredentialContainerSdktemplate();
        actionHandler.runAction(connectionData, entityName, rowData);

        //TODO - add a test here to verify that something happened
     }

}
