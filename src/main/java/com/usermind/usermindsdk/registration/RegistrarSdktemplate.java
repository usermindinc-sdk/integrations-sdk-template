package com.usermind.usermindsdk.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.registration.entities.Integration;
import com.usermind.usermindsdk.registration.entities.IntegrationType;
import com.usermind.usermindsdk.utils.IntegrationApiClient;
import com.usermind.usermindsdk.workerinformation.WorkerInfo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.function.Function;

@Component
public class RegistrarSdktemplate implements Registrar {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegistrarSdktemplate.class);

  private final String integrationResourceFileName = "IntegrationConfiguration.json";

  private final String integrationTypeResourceFileName = "IntegrationTypeConfiguration.json";

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  private final WorkerConfiguration workerConfiguration;

  private final WorkerInfo workerInfo;

  @Autowired
  public RegistrarSdktemplate(RestTemplate restTemplate, ObjectMapper objectMapper, WorkerConfiguration configuration,
                              WorkerInfo workerInfo) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.workerConfiguration = configuration;
    this.workerInfo = workerInfo;
  }

  @Override
  public String register() throws Exception {
    try {
      String integrationConfig = loadFileFixtureAsString(integrationResourceFileName);
      String integrationTypeConfig = loadFileFixtureAsString(integrationTypeResourceFileName);
      IntegrationApiClient apiClient = new IntegrationApiClient(restTemplate, workerConfiguration.getIntegrationRestApiUrl(), objectMapper);

      IntegrationType integrationType = new IntegrationType(objectMapper.readValue(integrationTypeConfig, Object.class), workerInfo.getWorkerType());

      String integrationTypeId = apiClient.register(integrationType);

      Integration integration = new Integration(objectMapper.readValue(integrationConfig, Object.class), integrationTypeId, workerInfo.getWorkerVersion());
      return apiClient.register(integration);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private <T> T loadFileFixture(String resourceName, Function<InputStream, T> read) throws IOException {
    try (InputStream is = getClass().getResourceAsStream(resourceName)) {
      return read.apply(is);
    }
  }

  private String loadFileFixtureAsString(String resourceName) throws IOException {
    return loadFileFixture(resourceName, input -> {
      try {
        return IOUtils.toString(input, Charset.defaultCharset());
      } catch (IOException e) {
        throw new RuntimeException("read file to string failed", e);
      }
    });
  }
}
