package com.usermind.usermindsdk;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.dropwizard.DropWizardService;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.helpers.JsonSerialization;
import com.usermind.usermindsdk.spring.BeanRegistration;
import io.dropwizard.jackson.Jackson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TestBase {

    protected static AnnotationConfigApplicationContext ctx;
    public static RestTemplate restTemplate = null;
    public static ObjectMapper objectMapper;

    public static ObjectMapper setupObjectMapper() {

        if (objectMapper == null) {
            objectMapper = Jackson.newObjectMapper();
            JsonSerialization.configure(objectMapper);
        }
        return objectMapper;
    }

    public static AnnotationConfigApplicationContext setUpSpring() {

        List<String> profiles = new ArrayList<>();
        profiles.add("test");

        AnnotationConfigApplicationContext acaContext = new AnnotationConfigApplicationContext();

        acaContext.getEnvironment().setActiveProfiles(profiles.toArray(new String[profiles.size()]));
        acaContext.register(DropWizardService.SPRING_CONFIG_CLASSES);

        BeanRegistration beanRegistration = new BeanRegistration();
        beanRegistration.registerClasses(acaContext, objectMapper, new WorkerConfiguration());

        return acaContext;
    }

    @BeforeAll
    public static final void configureExpectedClasses() {
        Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        setupObjectMapper();
        ctx = setUpSpring();
        ctx.refresh();
        restTemplate = ctx.getBean(RestTemplate.class);
    }

    @BeforeEach
    public final void TestBaseSetup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        //JUnit reuses threads. So if an interrupt is thrown in a test, it stays there - and when JUnit reuses that thread,
        //the interrupt is still there and interrupts the new thread. This was one of the uglier things I've debugged.
        Thread.currentThread().interrupted();
    }

    //For the loadFileFixture classes - the file has to be in a resource directory with the same name as the
    //class trying to use it, or else it won't be found
    protected <T> T loadFileFixture(String resourceName, Function<InputStream, T> read) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(resourceName)) {
            return read.apply(is);
        }
    }

    protected String loadFileFixtureAsString(String resourceName) throws IOException {
        return loadFileFixture(resourceName, input -> {
            try {
                return IOUtils.toString(input, Charset.defaultCharset());
            } catch (IOException e) {
                throw new RuntimeException("read file to string failed", e);
            }
        });
    }


}
