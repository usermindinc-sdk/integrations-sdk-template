package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceSdktemplateIT extends TestBase {

    private AuthenticationServiceSdktemplate authenticationService;
    @BeforeEach
    void setUp() throws IOException {
        authenticationService = ctx.getBean(AuthenticationServiceSdktemplate.class);;
    }

    @Test
    void basicTest() throws Exception {
        AuthenticatorResponse response = authenticationService.validate(TestClassFactory.getCredentialContainerSdktemplate());
        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);
//TODO SDK: Add in valid asserts to check the returned entities
//        assertThat(response.getEntities()).contains(FetchSetupSdktemplate.FOLLOWER_LIST);
        return;
    }

    @Test
    void invalidCredentialsTest() throws Exception {

        Assertions.assertThrows(InvalidCredentialsException.class, () -> {
            authenticationService.validate(TestClassFactory.getInvalidCredentialContainerSdktemplate());
        });

        return;
    }

}
