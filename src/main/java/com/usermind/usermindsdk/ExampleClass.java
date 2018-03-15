package com.usermind.usermindsdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ExampleClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleClass.class);

    @PostConstruct
    public void exampleMethod() {
        LOGGER.error("Hi!");
    }

}
