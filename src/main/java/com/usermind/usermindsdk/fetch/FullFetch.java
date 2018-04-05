package com.usermind.usermindsdk.fetch;


import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.baselib.writers.EntityWriter;
import com.usermind.usermindsdk.baselib.writers.s3.EntityS3Writer;
import com.usermind.usermindsdk.baselib.writers.s3.EntityS3WriterBuilder;
import com.usermind.usermindsdk.baselib.writers.s3.OnCloseNopConsumer;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
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

@Component
public class FullFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetch.class);

    private final WorkerConfiguration workerConfiguration;
    private final RunPoller runPoller;
    private final WorkerInfo workerInfo;

    public static final String AUTHORIZATION = "Authorization";

    private RestTemplate restTemplate;

    @Autowired
    public FullFetch(RestTemplate restTemplate, WorkerConfiguration workerConfiguration,
                     RunPoller runPoller, WorkerInfo workerInfo) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.workerInfo = workerInfo;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
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
                .path("/v2/" + runPoller.getAccountName() + "/events");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Token token=" + runPoller.getApiKey());
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Events> response = restTemplate.exchange(singleFieldBuilder.build(), HttpMethod.GET, entity, Events.class);

        try (EntityWriter entityWriter = createEntityS3Writer()) {
            entityWriter.writeFile("testMetaData", response.getBody().toString());
        }

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
        headers.add("Authorization", "Token token=" + runPoller.getApiKey());
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        response.getBody();
    }


    EntityWriter createEntityS3Writer(//IntegrationApiConnector apiConnector
                                       ) {

        return EntityS3WriterBuilder.newBuilder()
                .setRunPoller(runPoller)
                .setWorkerInfo(workerInfo)
                .setS3Config(workerConfiguration.getS3Config())
                .setOnCloseCheckpointsConsumer(new OnCloseNopConsumer())
                .build();
    }


//    private MetricsReporter<MetricsCollectorClient> createMetricsReporter(
//            /*IntegrationApiConnector apiConnector, */String actionName, WorkerInfo workerInfo) {
//        //path prefix should be integrations.tito.v1_0.fetch
//        String pathPrefix = new IntegrationMetricsPathBuilder()
//                .setFlowName(actionName)
//                .setIntegrationName(workerInfo.getWorkerType())
//                .setIntegrationVersion(workerInfo.getWorkerVersion())
//                .build();
//        MetricsCollectorClient metricsCollectorClient = new StatsDMetricsCollector(workerConfiguration.getIntegrationMetrics());
//        return CommonLibMetricsReporter.newBuilder()
//                .setPathPrefix(pathPrefix)
//                .addDefaultTag("connection", runPoller.getConnectionId()) //907c3155-0036-4d5e-a0f1-d0fab6d61d95
//                .addDefaultTag("run", runPoller.getRunId()) //557007ce-d629-47eb-8cf0-c8ad1d6d5608
//                .build(metricsCollectorClient);
//    }

}
