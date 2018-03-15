package com.usermind.usermindsdk.worker.adapter.db.fetchrecords;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadStates;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadMessagesRecord;
import com.usermind.usermindsdk.worker.flow.exception.NonRetriableIntegrationException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Wave class which manages db access to the FAQWAD table and wave queue.
 */
public class Wave {

  private static Logger logger = LoggerFactory.getLogger(Wave.class);
  private static final String queueName = "wave_queue";
  private static final String blockedStateName = "Blocked";
  private static final String typeName = "Wave_Fetch";
  private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

  public Wave() {
  }

  /**
   * Insert a FAQWADMessage into the FAQWAD table.
   * Below is the mapping between the positional parameters and
   * their expected values
   * --      String queueName {0},
   * --      String stateName {1},
   * --      String typeName {2},
   * --      Long   organizationId {3},
   * --      String objectId {4},
   * --      String singletonId {5},
   * --      Long   messageTimestamp {6},  // defaults to db time if null
   * --      Long   messageOffset {7},
   * --      String messageData {8},
   * --      Long   priority  {9}         // defaults to 100 if null
   * --      )
   * @param create DSLContext database transaction
   * @param organizationId organization id
   * @param legacyConnectionId legacy connection id
   * @param runId run id
   * @return Faqwad Message Record inserted into the database
   * @throws JsonProcessingException Issue parsing json in the messageData column
   */
  public FaqwadMessagesRecord createFaqwadMessage(DSLContext create,
                                                  long organizationId,
                                                  long legacyConnectionId,
                                                  String runId) throws JsonProcessingException {
    Long messageTimestamp = null;
    Long priority = null;
    String messageData = getMessageData(organizationId, legacyConnectionId, runId);

    return this.executeQueryReturnSingleRecord(create, WaveQueries.createFAQWADMessage,
      queueName,
      blockedStateName,
      typeName,
      organizationId,
      organizationId,
      runId, //We are using the runId as the singleton id
      messageTimestamp,
      0,
      messageData,
      priority);
  }

  /**
   * Update a message to the queued state
   * -- MarkQueued(fm_id, rvn)
   * --  fm_id {0}
   * --  rvn {1}
   * -- Only works for things that were Blocked. The next_retry_time_utc
   * -- is untouched as nothing has been trying let-alone retrying.
   *
   * @param create DSLContext database transaction
   * @param messageId message id
   * @param rvn version
   * @return Faqwad Message Record updated in the database
   * @throws JsonProcessingException Issue parsing json in the messageData column
   */
  public FaqwadMessagesRecord markQueued(DSLContext create, long messageId, long rvn)
    throws JsonProcessingException {
    return this.executeQueryReturnSingleRecord(create, WaveQueries.markQueued, messageId, rvn);
  }

  /**
   * Gets the unique openMessage (not in terminal state) using the objectId and singletonId
   * -- String queueName {0},
   * -- String objectId {1}
   * -- String singletonId {2}
   * --
   * -- Used to find what messages are open with the specific
   * -- queue scope with the given singleton_id value. This list
   * -- does not include the faqwad messages that are in terminal states.
   *
   * @param create DSLContext database transaction
   * @param orgId organization id
   * @param runId run id
   * @return Faqwad Message Record found in the database
   * @throws JsonProcessingException Issue parsing json in the messageData column
   */
  public FaqwadMessagesRecord getOpenMessageFromSingletonId(DSLContext create,
                                                            long orgId,
                                                            String runId)
      throws JsonProcessingException {
    //Note that objectId is a string which is why we need to cast the orgId into a string.
    return this.executeQueryReturnSingleRecord(
      create, WaveQueries.getOpenMessageFromSingletonId, queueName, Long.toString(orgId), runId);
  }

  /**
   * Gets a list of blocked message which age is older than maxAgeInSeconds
   * -- String queueName {0},
   * -- Long ageInSeconds {1}
   * --
   * -- Used to find what messages are Blocked the in the specific queue scope
   * -- where the current time minus the creation date is larger than ageInSeconds.
   *
   * @param create DSLContext database transaction
   * @param maxAgeInSeconds time to search
   * @return List of Faqwad Message Records found in the database
   */
  public List<FaqwadMessagesRecord> getBlockedMessageOlderThan(DSLContext create,
                                                               long maxAgeInSeconds) {
    ResultQuery<Record> query = create.resultQuery(
        WaveQueries.getBlockedMessageOlderThan, queueName, maxAgeInSeconds);

    Result<Record> result = create.fetch(query);
    if (result == null) {
      return new ArrayList<>();
    }
    return result.into(FaqwadMessagesRecord.class);
  }

  /**
   * Returns a wave state name from and state id.
   *
   * @param create DSLContext database transaction
   * @param id wave id
   * @return Name of the Wave
   */
  public String getStateNameFromId(DSLContext create, long id) {
    Record result = create.select(FaqwadStates.FAQWAD_STATES.FAQWAD_STATE_NAME)
        .from(FaqwadStates.FAQWAD_STATES)
        .where(FaqwadStates.FAQWAD_STATES.FAQWAD_STATE_ID.eq(id))
        .fetchOne();
    if (result == null) {
      //what exception type ??
      throw new NonRetriableIntegrationException(
          String.format("No statename found for FAQWAD state id %s", id));
    }
    return result.into(String.class);
  }

  private FaqwadMessagesRecord executeQueryReturnSingleRecord(DSLContext create,
                                                              String queryString,
                                                              Object... bindings) {
    ResultQuery<Record> query = create.resultQuery(queryString, bindings);

    Record record = create.fetchOne(query);
    if (record == null) {
      return null;
    }
    return record.into(FaqwadMessagesRecord.class);
  }

  private String getMessageData(Long orgId, long legacyConnectionId, String runId)
      throws JsonProcessingException {
    MessageData messageData = new MessageData(orgId, legacyConnectionId, runId);
    return DEFAULT_MAPPER.writeValueAsString(messageData);
  }

  //TODO use lombok. Most probably due to the use of ajc compiler instead of javac.
  //Would need to spend more time looking into this.
  public static class MessageData {
    long orgId;
    long legacyConnectionId;
    String runId;

    //used for serialization
    public MessageData() {

    }

    /**
     * Constructor.
     *
     * @param orgId org Id
     * @param legacyConnectionId legacy Connection Id
     * @param runId run Id
     */
    public MessageData(long orgId, long legacyConnectionId, String runId) {
      this.orgId = orgId;
      this.legacyConnectionId = legacyConnectionId;
      this.runId = runId;
    }

    /**
     * Returns the run id.
     *
     * @return runId
     */
    public String getRunId() {
      return runId;
    }

    /**
     * Returns the legacy connection id.
     *
     * @return legacyConnectionId
     */
    public long getLegacyConnectionId() {
      return legacyConnectionId;
    }

    /**
     * Returns the orgId.
     *
     * @return orgId
     */
    public long getOrgId() {
      return orgId;
    }
  }
}
