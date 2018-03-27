package com.usermind.usermindsdk.baselib.metrics;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkState;

/**
 * Integration metrics path builder - this is to keep the metrics path format unified among
 * the integrations.
 */
public class IntegrationMetricsPathBuilder {

  private String integrationName;
  private String integrationVersion;
  private String flowName;

  public IntegrationMetricsPathBuilder setIntegrationName(String integrationName) {
    this.integrationName = integrationName;
    return this;
  }

  public IntegrationMetricsPathBuilder setIntegrationVersion(String integrationVersion) {
    this.integrationVersion = integrationVersion;
    return this;
  }

  public IntegrationMetricsPathBuilder setFlowName(String flowName) {
    this.flowName = flowName;
    return this;
  }

  /**
   * Builds a metrics path. Replaces "." in version with "_".
   * @return metrics path
   */
  public String build() {
    checkState(!Strings.isNullOrEmpty(integrationName));
    checkState(!Strings.isNullOrEmpty(integrationVersion));

    String normalizedVersion = "v" + integrationVersion.replace("v", "").replace(".", "_");

    return Joiner
        .on(".")
        .skipNulls()
        .join("integrations", integrationName, normalizedVersion, flowName);
  }
}
