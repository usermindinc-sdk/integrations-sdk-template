package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
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

import java.util.concurrent.TimeUnit;

@Component
public class FullFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(FullFetch.class);

    private final RunPoller runPoller;
    private final MetadataFetch metadataFetch;

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_STRING = "Token token=";

    private RestTemplate restTemplate;

    @Autowired
    public FullFetch(RestTemplate restTemplate, RunPoller runPoller, MetadataFetch metadataFetch) {
        this.restTemplate = restTemplate;
        this.runPoller = runPoller;
        this.metadataFetch = metadataFetch;
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
        Events events = metadataFetch.runMetadataFetch(runPoller.getAccountName(), runPoller.getApiKey());
        getAllRegistrations(events);
        return;
    }

    protected void getAllRegistrations(Events events) {
        events.getData().stream()
                .map(event -> event.getLinks().getSelf() + "/registrations")
                .forEach(url -> getEventRegistrations(url));
    }

    private void getEventRegistrations(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, TOKEN_STRING + runPoller.getApiKey());
        headers.add(org.apache.http.HttpHeaders.ACCEPT, "application/vnd.api+json");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Registrations> response = restTemplate.exchange(url, HttpMethod.GET, entity, Registrations.class);

        response.getBody();
    }


//    EntityWriter createEntityS3Writer(//IntegrationApiConnector apiConnector
//                                       ) {
//
//        return EntityS3WriterBuilder.newBuilder()
//                .setRunPoller(runPoller)
//                .setWorkerInfo(workerInfo)
//                .setS3Config(workerConfiguration.getS3Config())
//                .setOnCloseCheckpointsConsumer(new OnCloseNopConsumer())
//                .build();
//    }


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