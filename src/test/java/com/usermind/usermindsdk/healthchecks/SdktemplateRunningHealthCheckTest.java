package com.usermind.usermindsdk.healthchecks;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateRunningHealthCheckTest extends TestBase {

    SdktemplateRunningHealthCheck healthCheck;

    @BeforeEach
    void setUp() throws Exception {
        healthCheck = new SdktemplateRunningHealthCheck();
    }

    @Test
    void runHealthCheck() throws Exception {
        assertThat(healthCheck.check().isHealthy());
    }

}
