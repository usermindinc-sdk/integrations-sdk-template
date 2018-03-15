package com.usermind.usermindsdk.worker.adapter.writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeTraverser;
import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.adapter.db.DataSourceHolder;
import com.usermind.usermindsdk.worker.adapter.db.DbTxManager;
import com.usermind.usermindsdk.worker.adapter.db.actions.ActionJournal;
import com.usermind.usermindsdk.worker.adapter.db.fetchrecords.FetchRecord;
import com.usermind.usermindsdk.worker.adapter.db.fetchrecords.Wave;
import com.usermind.usermindsdk.worker.adapter.db.metadata.*;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadMessagesRecord;
import com.usermind.usermindsdk.worker.normalization.metadata.MetadataOutputVersion;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;


/**
 * LC39Writer.
 */
public class LC39Writer {

  private static Logger logger = LoggerFactory.getLogger(LC39Writer.class);

  // this is thanks to the property names and the typesafe config.
  private static String tripleQuote(String inputString) {
    return "\"\"\"" + inputString + "\"\"\"";
  }

  private final DbTxManager txManager;
  private final Wave wave;
  private final HashMap<String, Entity> entityHashMap;

  /**
   * build the Lc39 writer.
   *
   * @param dataSourceHolder to use
   */
  public LC39Writer(DataSourceHolder dataSourceHolder) {
    this(dataSourceHolder, new Wave());
  }

  /**
   * Testability constructor. Used by unit tests to inject a mocked wave objct
   *
   * @param dataSourceHolder DatasourceHolder holding the connectionPool
   * @param wave Wave object
   */
  public LC39Writer(DataSourceHolder dataSourceHolder, Wave wave) {
    this.txManager = new DbTxManager(dataSourceHolder);
    this.entityHashMap = new HashMap<>();
    this.wave = wave;
  }

  /**
   * add record to metadata.
   *
   * @param entityType fetched entity type
   * @param recordData fetched record data
   * @return true if added to md
   */
  public boolean addEntity(String entityType, Configuration recordData) {
    MetadataOutputVersion metadataVersion = getMetadataVersion(recordData);

    String entityDisplayName = entityType;
    if (metadataVersion == MetadataOutputVersion.V2) {
      entityDisplayName = recordData.getString("display_name");
      recordData = recordData.atPath("fields");
    }

    for (String property : recordData.getPaths(Optional.empty())) {
      String typeName;
      String displayName;
      boolean isRequired;
      Optional<Boolean> readOnly;
      String escapedProperty = tripleQuote(property);
      if (recordData.getFieldType(escapedProperty) == ConfigValueType.OBJECT) {
        Configuration propertyValue = recordData.atPath(escapedProperty);
        typeName = propertyValue.getString("type");
        displayName = propertyValue.getString("display_name", property);
        isRequired = propertyValue.getBoolean("is_required", false);
        readOnly = propertyValue.getBooleanAsOptional("read_only");
      } else {
        typeName = recordData.getString(escapedProperty);
        displayName = property;
        isRequired = false;
        readOnly = Optional.empty();
      }
      addEntityWithFieldDescription(entityType, entityDisplayName, property, typeName, displayName,
          isRequired, readOnly);
    }
    return true;
  }

  /**
   * updates lc39 db with the fetchrecords for the current fetch wave.
   * @param create          DSLContext to use
   * @param s3WritersConfig configurations associated with the s3writers
   * @param fetchRecordStatus fetch record status
   */
  public void updateFetchRecords(DSLContext create, List<Configuration> s3WritersConfig,
                                 Optional<String> fetchRecordStatus) {
    for (Configuration configuration : s3WritersConfig) {
      new FetchRecord(configuration).update(create, fetchRecordStatus);
    }
  }

  /**
   * Updates LC39 db with the success state of action runs.
   *
   * @param create    DSLContext to use
   * @param actionId  actionId
   * @param status    status
   */
  private void updateActionJournalState(DSLContext create, long actionId,
                                        ActionJournal.Status status) {
    new ActionJournal(actionId).updateStatus(create, status);
  }


  /**
   * updates lc39 db with the metadata from the current fetch wave.
   *
   * @param create    DSLContext to use
   * @param orgId     orgId
   * @param channelId channelId
   */
  public void updateMetadata(DSLContext create, long orgId, long channelId) {
    for (Map.Entry<String, Entity> entityEntry : entityHashMap.entrySet()) {
      entityEntry.getValue().update(orgId, channelId, create);
    }
  }

  /**
   * update lc39 channel hasentities flag.
   * @param create DSLContext to use
   * @param channelId channelId
   * @param canHaz flag
   */
  public void updateHasEntities(DSLContext create, long channelId, boolean canHaz) {
    new Channel(channelId).updateHasEntities(canHaz, create);
  }

  /**
   * update lc39 channel hasactions flag.
   * @param create DSLContext to use
   * @param channelId channelId
   * @param canHaz flag
   */
  public void updateHasActions(DSLContext create, long channelId, boolean canHaz) {
    new Channel(channelId).updateHasActions(canHaz, create);
  }

  /**
   * updates lc39 db with the metadata for the associated actions.
   *
   * @param orgId     orgId
   * @param channelId channelId
   * @param actionsMeta actions metadata to update
   * @throws SQLException throw if issue is encountered
   */
  public void updateActionsMetadata(long orgId, long channelId,
                                    Map<Configuration, List<Configuration>> actionsMeta)
      throws SQLException {
    if (!actionsMeta.isEmpty()) {
      Iterable<Configuration> actionsIterable = buildActionsTreeIterable(actionsMeta.keySet());

      txManager.withTransaction(transactional -> {
          // saved actions and their ids
          Map<String, Long> updatedActions = new HashMap<>();
          //delete existing meta actions for the given org and channel
          MetaActions metaActions = new MetaActions(orgId, channelId);
          metaActions.delete(DSL.using(transactional));
          // process actions
          for (Configuration action : actionsIterable) {
            Optional<Boolean> hasActions = action.hasPath("hasActions")
                ? Optional.of(action.getBoolean("hasActions"))
                : Optional.empty();

            Optional<String> forEntity = action.hasPath("forEntity")
                ? Optional.of(action.getString("forEntity"))
                : Optional.empty();

            Optional<String> parentAction = action.hasPath("parentAction")
                ? Optional.of(action.getString("parentAction"))
                : Optional.empty();

            // as parents are processed before children
            // their ids should be present in updatedActions
            Optional<Long> parentId =
                parentAction.flatMap(pa -> Optional.ofNullable(updatedActions.get(pa)));

            String actionName = action.getString("name");
            long actionId =
                new Action(action.getLong("orgId"), action.getLong("channelId"), actionName)
                  .create(action.getString("displayName"), action.getBoolean("hasVariables"),
                    hasActions, forEntity, parentId, DSL.using(transactional));
            updateHasActions(DSL.using(transactional), action.getLong("channelId"), true);
            // cache updated action into updatedActions
            updatedActions.put(actionName, actionId);

            // process action args
            for (Configuration actionArg : actionsMeta.get(action)) {
              String path = actionArg.getString("path", null);
              ActionArg argEntity =
                  new ActionArg(actionArg.getLong("orgId"), actionArg.getLong("channelId"),
                    actionArg.getString("actionName"), actionArg.getString("name"));
              argEntity
                  .create(actionArg.getString("displayName"), actionArg.getBoolean("assignable"),
                    actionArg.getString("type"), path, DSL.using(transactional));
            }
          }
        });
    }
  }

  /**
   * does all of the lc39 writes in the context of a transaction.
   *
   * @param fetchRecordConfigurations fetched records configurations
   * @param organizationId organization Id
   * @param channelId channel Id
   * @param fetchRecordStatus fetch record status
   * @param runId run Id
   * @throws SQLException throw if issue is encountered
   */
  public void write(List<Configuration> fetchRecordConfigurations, long organizationId,
                    long channelId, String runId, Optional<String> fetchRecordStatus)
                    throws SQLException {

      txManager.withTransaction(transactional -> {
        try {
          updateFetchRecords(DSL.using(transactional), fetchRecordConfigurations,
              fetchRecordStatus);
        }
        catch (IllegalStateException ex) {
          CommonLib.get().getMetrics().incrementCounter("integrations.lc39.failure",
              "type:UpdateFetchRecords");
          logger.warn("Writes context of a transaction with IllegalStateException", ex.getMessage());
        }
        updateMetadata(DSL.using(transactional), organizationId, channelId);
        updateHasEntities(DSL.using(transactional), channelId, true);
        finishWaveInternal(DSL.using(transactional), organizationId, runId);
      });
  }

  /**
   * Updates the action related data in lc39 in the context of a transaction.
   *
   * @param actionId action Id
   * @param status ActionJournal Status
   * @throws SQLException throw if an issue is encountered
   */
  public void writeAction(long actionId, ActionJournal.Status status) throws SQLException {
    txManager.withTransaction(transactional -> {
        updateActionJournalState(DSL.using(transactional), actionId, status);
      });
  }

  /**
   * Creates a wave record in the Faqwad table for the run.
   * @param legacyConnectionId legacy connection Id
   * @param organizationId organization Id
   * @param runId run Id
   * @return Returns the inserted Faqwad message record.
   *         Null if nothing was inserted because there was already a record for the runId.
   * @throws SQLException sqlException
   */
  public FaqwadMessagesRecord createWave(long legacyConnectionId, long organizationId, String runId)
        throws SQLException {
    FaqwadMessagesRecord record = txManager.withTransactionResult(transactional ->
        createWaveInternal(DSL.using(transactional), legacyConnectionId, organizationId, runId));
    return record;
  }

  /*
   * This method is package internal only and is not meant to be called !!!
   * This is added so that we can unit test the logic...
   * because the txManager is kind of a pain in the ass to test otherwise...
   */
  FaqwadMessagesRecord createWaveInternal(DSLContext context,
                                          long legacyConnectionId,
                                          long organizationId,
                                          String runId)
    throws JsonProcessingException {
    try {
      return wave.createFaqwadMessage(context, organizationId, legacyConnectionId, runId);
    } catch (DataAccessException dataAccessException) {
      if (isExceptionRaisedException(dataAccessException)) {
        // We just swallow the exception since this means that a row with the current run
        // already exist.
        CommonLib.get().getMetrics().incrementCounter("integrations.lc39.failure",
            "type:WaveRecordInsertFailure");
        logger.warn("Raised exception received when trying to create a wave for "
            + "organizationId={}, legacyConnectionId={}, runId={}. Exception :",
            organizationId, legacyConnectionId, runId, dataAccessException);
        return null;
      }
      throw dataAccessException;
    }
  }

  /**
   * Update the status of a 'Blocked' wave record to 'Queued'
   * @param organizationId organizationId
   * @param runId runId
   * @return Returns the latest state of the faqwad message record
   *         for the given organizationId, runId.
   *         The method tries to update the status of the message to 'queued'.
   *         The return value would be either : the updated record
   *         or the old record if the status could not be updated
   *         (i.e. the record stats was not in blocked since the only
   *         transition available to queued is from blocked.)
   *         The method will return null if a record was not found
   *         for the given organizationId, runId.
   *
   *         NOTE: careful to keep this one under 1 transaction.
   *         No test right now for behavior under concurrency.
   * @throws SQLException sqlException
   */
  public FaqwadMessagesRecord finishWave(long organizationId, String runId) throws SQLException {
    return txManager.withTransactionResult(transactional ->
      finishWaveInternal(DSL.using(transactional), organizationId, runId));
  }

  FaqwadMessagesRecord finishWaveInternal(DSLContext context, long organizationId, String runId)
      throws JsonProcessingException {
    FaqwadMessagesRecord record = wave.getOpenMessageFromSingletonId(
        context, organizationId, runId);
    if (record != null) {
      FaqwadMessagesRecord newRecord = wave.markQueued(
          context, record.getFaqwadMessageId(), record.getRvn());
      if (newRecord == null) {
        return record;
      }
      return newRecord;
    }
    CommonLib.get().getMetrics().incrementCounter("integrations.lc39.failure",
        "type:WaveRecordNotFound");
    logger.warn("Unable to mark the wave finished because no record "
        + "found for organizationId={} and runId={}.",
        organizationId, runId);
    return null;
  }

  /**
   * Returns all messages which age is older than maxAgeInSec.
   * @param maxAgeInSec maxAgeInSec
   * @return empty list if no message matches the conditions
   * @throws SQLException sqlException
   */
  public List<FaqwadMessagesRecord> getBlockedWaves(long maxAgeInSec) throws SQLException {
    return txManager.withTransactionResult(transactional ->
      wave.getBlockedMessageOlderThan(DSL.using(transactional), maxAgeInSec));
  }

  private boolean isExceptionRaisedException(DataAccessException dataAccessException) {
    //https://www.postgresql.org/docs/9.1/static/errcodes-appendix.html
    //P0001 is the sql code for raise_exception. This is what the stored procedure throw as
    // exception
    // say if a singleton constraint is violated.
    return dataAccessException.sqlState().equals("P0001");
  }



  private void addEntityWithFieldDescription(String entity, String entityDisplayName, String field,
      String value, String fieldDisplayName, boolean isRequired, Optional<Boolean> readOnly) {
    entityHashMap.putIfAbsent(entity, new Entity(entity, entityDisplayName));
    entityHashMap.get(entity).addField(field, value, fieldDisplayName, isRequired, readOnly);
  }

  /**
   * Creates an preorder Iterable for action tree.
   * <p>
   * Note:
   * </p>
   * As an action can have parent action we have to ensure that parent action will be processed
   * before child action. So here we build an tree preorder iterable which will preserve
   * the 'parents and then children' order.
   *
   * @see TreeTraverser
   * @param actions actions to be processed.
   * @return a tree preorder traversal
   */
  private Iterable<Configuration> buildActionsTreeIterable(Collection<Configuration> actions) {
    // <parent_name, children>
    Multimap<String, Configuration> parentsAndChildren = LinkedListMultimap.create(actions.size());

    for (Configuration action : actions) {
      // make actions without parent under __ROOT__ node (needed for TreeTraverser)
      String parentName = action.hasPath("parentAction")
          ? action.getString("parentAction")
          : "__ROOT__";
      parentsAndChildren.put(parentName, action);
    }

    TreeTraverser<Configuration> treeTraverser = new TreeTraverser<Configuration>() {
      @Override
      public Iterable<Configuration> children(Configuration item) {
        return parentsAndChildren.get(item.getString("name"));
      }
    };

    // define root node (needed for TreeTraverser)
    Configuration root = new ConfigurationBuilder().put("name", "__ROOT__").toConfiguration();

    FluentIterable<Configuration> iterable = treeTraverser
        .preOrderTraversal(root);

    // ignore root
    return iterable.skip(1);
  }

  private static MetadataOutputVersion getMetadataVersion(Configuration payload) {
    MetadataOutputVersion metadataOutputVersion = MetadataOutputVersion.V1;
    if (payload.hasPath("version") && payload.getFieldType("version") == ConfigValueType.STRING) {
      metadataOutputVersion = MetadataOutputVersion.valueOf(payload.getString("version"));
    }
    return metadataOutputVersion;
  }

  /**
   * Build the default LC39 connection config.
   *
   * @return connection configuration
   */
  public static final Configuration getLC39DatabaseConfig() {
    Configuration legacyConfig = CommonLib.get().getConfiguration();
    legacyConfig.checkPathExists("integrationsWorker.legacyConfig.lc39Writer");
    return legacyConfig.atPath("integrationsWorker.legacyConfig.lc39Writer");
  }
}
