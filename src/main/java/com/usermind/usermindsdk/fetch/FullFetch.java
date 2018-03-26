package com.usermind.usermindsdk.fetch;


import com.usermind.usermindsdk.baselib.metrics.StatsDMetricsCollector;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.DefaultConfigurationSource;
import com.usermind.usermindsdk.baselib.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.baselib.metrics.reporter.CommonLibMetricsReporter;
import com.usermind.usermindsdk.baselib.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.WorkerInfo;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.worker.util.IntegrationMetricsPathBuilder;
import com.usermind.usermindsdk.worker.writers.EntityWriter;
import com.usermind.usermindsdk.worker.writers.s3.EntityS3Writer;
import com.usermind.usermindsdk.worker.writers.s3.OnCloseNopConsumer;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.UriBuilder;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class FullFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetch.class);

    public static final String ORG_ID = "100";
    public static final String LEGACY_ID = "101";
    public static final String RUN_ID = "1020926111074";
    public static final String CONNECTION_ID = "907c3155-0036-4d5e-a0f1-d0fab6d61d95";

    private WorkerConfiguration workerConfiguration;

    public static final String AUTHORIZATION = "Authorization";
    private final MetricsReporter<MetricsCollectorClient> metricsReporter;
    private Credentials credentials = new Credentials("nM_bPyV4sfbVBz8Po28g", "ragi-test");
    public static class Credentials {
        private final String apiKey;
        private final String accountName;


        private Credentials(String apiKey, String accountName) {
            checkArgument(isNotBlank(apiKey));
            checkArgument(isNotBlank(accountName));
            this.apiKey = apiKey;
            this.accountName = accountName;
        }

        private String getApiKey() {
            return apiKey;
        }

        private String getAccountName() {
            return accountName;
        }

        private String getAuthorizationToken() {
            return String.format("Token token=%s", apiKey);
        }

    }
    private RestTemplate restTemplate;

    @Autowired
    public FullFetch(RestTemplate restTemplate, WorkerConfiguration workerConfiguration) {
        this.restTemplate = restTemplate;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        metricsReporter = createMetricsReporter("FullFetch");
        LOGGER.info("Setup took {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
      //  Configuration usermindConfiguration = new DefaultConfigurationSource().load();
        this.workerConfiguration = workerConfiguration;
        stopWatch.stop();

        LOGGER.info("Setup took {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
        return;
    }
    //Takes 8 to 9 minutes in the old code
    //New code:
    //Just getting data from Tito: 27 seconds
    //Parallel threads: 15 seconds

    //Creating the metrics class and reading the configuration data - also 15 seconds
    //Took 7 to set up metrics, 8 to read the config file

    public void runFullFetch() {
        //For Tito - this is hard coded. Fetch the registrations:
        //https://api.tito.io/timeline
        //Then for each registration, fetch the attendees:
        //https://api.tito.io/v2/ragi-test/2016-edition/registrations
        //https://api.tito.io/v2/ragi-test/2017-edition/registrations

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        getEvents();

        stopWatch.stop();

        LOGGER.info("Full fetch took {} seconds", stopWatch.getTime(TimeUnit.SECONDS));
        return;
    }


    protected void getEvents() {
        UriBuilder singleFieldBuilder = UriBuilder
                .fromPath("https://api.tito.io")
                .path("/v2/ragi-test/events");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token token=nM_bPyV4sfbVBz8Po28g");
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Events> response = restTemplate.exchange(singleFieldBuilder.build(), HttpMethod.GET, entity, Events.class);

//        try (EntityWriter entityWriter = createEntityS3Writer(metricsReporter)) {
//            entityWriter.writeMetadata(WorkerInfo.WORKER_TYPE, metadata);
//        }

        getAllRegistrations(response.getBody());

        return;
    }

    protected void getAllRegistrations(Events events) {
        events.getData().stream()
                .map(event -> event.getLinks().getSelf() + "/registrations")
                .forEach(url -> getEventRegistrations(url));

    }

    private void getEventRegistrations(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token token=nM_bPyV4sfbVBz8Po28g");
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        response.getBody();
    }


//    EntityWriter createEntityS3Writer(//IntegrationApiConnector apiConnector,
//                                      MetricsReporter<MetricsCollectorClient> metricsReporter) {
//
//        return EntityS3Writer.newBuilder()
//                .setWriterConfigPath("integrationsWorker.s3Writer")
//                .setMetricsReporter(metricsReporter)
//              //  .setApiConnector(apiConnector)
//                .setOnCloseCheckpointsConsumer(new OnCloseNopConsumer())
//                .build();
//    }


    private MetricsReporter<MetricsCollectorClient> createMetricsReporter(
            /*IntegrationApiConnector apiConnector, */String actionName) {
        //path prefix should be integrations.tito.v1_0.fetch
        String pathPrefix = new IntegrationMetricsPathBuilder()
                .setFlowName(actionName)
                .setIntegrationName(WorkerInfo.WORKER_TYPE)
                .setIntegrationVersion(WorkerInfo.WORKER_VERSION)
                .build();
        MetricsCollectorClient metricsCollectorClient = new StatsDMetricsCollector(workerConfiguration.getMetrics());
        return CommonLibMetricsReporter.newBuilder()
                .setPathPrefix(pathPrefix)
                .addDefaultTag("connection", CONNECTION_ID) //907c3155-0036-4d5e-a0f1-d0fab6d61d95
                .addDefaultTag("run", RUN_ID) //557007ce-d629-47eb-8cf0-c8ad1d6d5608
                .build(metricsCollectorClient);
    }

}
