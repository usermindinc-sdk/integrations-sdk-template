package com.usermind.usermindsdk.worker.base;

import java.util.List;

/**
 * describes all the versions of this integrations that can be handled.
 */
public interface IntegrationDescription {

  /**
   * get a list of all versions of integration that the code can handle.
   * @return the list
   */
  List<IntegrationVersionDescription> getIntegrationVersionDescriptions();

}
