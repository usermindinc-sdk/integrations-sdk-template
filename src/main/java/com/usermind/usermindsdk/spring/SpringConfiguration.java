package com.usermind.usermindsdk.spring;

import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = { "com.usermind" })
public class SpringConfiguration {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory httpClientFactory) {
        return new RestTemplate(httpClientFactory);
        //If you need to add message converters to RestTemplate, this is a good place to do it.
        //Similarly you can make it return an error instead of throwing an exception here if desired.
    }

//    @Bean
//    public BasicAuthRestTemplate restTemplate() {
//        return new BasicAuthRestTemplate("ragi-test", "nM_bPyV4sfbVBz8Po28g");
//        //If you need to add message converters to RestTemplate, this is a good place to do it.
//        //Similarly you can make it return an error instead of throwing an exception here if desired.
//    }

    /**
     * Provide an Apache HTTP Components HTTP Client
     */
    @Bean
    public ClientHttpRequestFactory httpClientFactory() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        return new PreemptiveDigestAuthenticationRequestFactory(httpclient);
    }

}
