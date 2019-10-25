package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddAuthenticationInformationSdktemplateTest {

    @Test
    void validTest() throws Exception{
        String testLink = "https://example.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("keyOne", "valueOne");
        headers.put("keyTwo", "valueTwo");
        String resultLink = new AddAuthenticationInformationSdktemplate()
                .putAuthIntoWebRequest(TestClassFactory.getCredentialContainerSdktemplate(), testLink, headers);

        assertThat(resultLink).isNotEmpty();
        assertThat(headers.get("keyOne")).isEqualTo("valueOne");
        assertThat(headers.get("keyTwo")).isEqualTo("valueTwo");
    }

    @Test
    void nullConnData() throws Exception {
        String testLink = "https://example.com";
        assertThrows(NullPointerException.class, ()->new AddAuthenticationInformationSdktemplate()
                .putAuthIntoWebRequest(null, testLink, new HashMap<>()));
    }

    @Test
    void nullMap() throws Exception {
        String testLink = "https://example.com";
        assertThrows(NullPointerException.class, ()->new AddAuthenticationInformationSdktemplate()
                .putAuthIntoWebRequest(TestClassFactory.getCredentialContainerSdktemplate(), testLink, null));
    }
}