package com.usermind.usermindsdk.worker.base;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.amazonaws.services.simpleworkflow.flow.core.Settable;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.adapter.db.DataSourceHolder;
import com.usermind.usermindsdk.worker.util.IntegrationVersionDescriptionUtil;

import java.util.Optional;

/**
 * description of all the parameters associated with one version of the integration.
 */
public abstract class IntegrationVersionDescription {

  protected Settable<AmazonSimpleWorkflow> service = new Settable<>();
  protected Settable<String> domain = new Settable<>();
  protected Settable<String> taskListToPoll = new Settable<>();

  /**
   * builds and gets the configuration associated with this version.
   * @return the configuration
   */
  public abstract Configuration getConfiguration();

  /**
   * builds and gets the dynamic configuration associated with this version.
   * @param connector {@code IntegrationApiConnector}
   * @return {@code Optional} of the configuration
   */
  public Optional<Configuration> getDynamicConfiguration(IntegrationApiConnector connector) {
    return Optional.empty();
  }

  /**
   * build a worker that poll and runs all activities associated with this version.
   * @return the worker
   * @throws Exception possible exception while building the worker
   */
  protected abstract ActivityWorker buildActivityWorker() throws Exception;

  /**
   * build a worker that poll and runs all activities associated with this version.
   * Should be overridden to take advantage of the dataSourceHolder!
   * @return the worker
   * @throws Exception possible exception while building the worker
   */
  public ActivityWorker buildActivityWorker(DataSourceHolder dataSourceHolder) throws Exception  {
    return buildActivityWorker();
  }

  /**
   * build a worker that poll and runs all workflows (ie actions) associated with this version.
   * @return the worker
   * @throws Exception possible exception while building the worker
   */
  public abstract WorkflowWorker buildWorkflowWorker() throws Exception;

  /**
   * triggers the specified action
   * @param id uniquely identified the run for this action. if null will autogenerate
   * @param action specifies the action to perform
   * @param input specifies the input associated with the action
   */
  public abstract void start(String id, String action, Configuration input);

  /**
   * sets the simple workflow (swf) parameters associated with this integration version.
   * @param service the swf service we will use
   * @param domain the domain with in swf that we want to use
   * @param taskListToPoll the task list within the domain that we want to use
   */
  public void setSimpleWorkflowParams(AmazonSimpleWorkflow service, String domain,
                                      String taskListToPoll) {
    this.service.set(service);
    this.domain.set(domain);
    this.taskListToPoll.set(taskListToPoll);
  }

  /**
   * returns the id associated with this integrations.
   *
   * <p>
   * default id you get out of the box in the form of integrationType-integrationVersion
   * you can override this if you have a special use case -  although overriding should be the
   * exception not the rule
   * @return id of integrations
   */
  public String getId() {
    return IntegrationVersionDescriptionUtil.generateWorkflowId(getConfiguration());
  }

  /**
   * Indicates whether this integration version should be registered with the API. Some Integrations Versions
   * are used only by the Integration Runner to manage internal workflows and are not designed to be registered
   * for external use. For most integrations this should be true, but the driver and the run-launcher override
   * this to false.
   *
   * @return true if this is an external integration, or false if it's an internal implementation detail
   */
  public boolean isExternalIntegration() {
    return true;
  }
}
