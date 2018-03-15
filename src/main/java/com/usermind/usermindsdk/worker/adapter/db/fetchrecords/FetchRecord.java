package com.usermind.usermindsdk.worker.adapter.db.fetchrecords;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FetchrecordsRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Fetchrecords.FETCHRECORDS;

/**
 * FetchRecord.
 */
public class FetchRecord {

  private static Logger logger = LoggerFactory.getLogger(FetchRecord.class);
  private static final String DEFAULT_STATUS = "persisted";

  private String organizationId;
  private String channelName;
  private String timestamp;
  private String name;
  private String entityType;
  private String path;
  private String jobId;

  /**
   * build the FetchRecord db structure.
   * @param writer configuration
   */
  public FetchRecord(Configuration writer) {
    organizationId = writer.getString("organizationId");
    channelName = writer.getString("channelName");
    timestamp = writer.getString("timestamp");
    name = writer.getString("fileName");
    entityType = writer.getString("entityType");
    path = writer.getString("path");
    jobId = writer.getString("jobId");
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public String getChannelName() {
    return channelName;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getName() {
    return name;
  }

  public String getEntityType() {
    return entityType;
  }

  public String getJobId() {
    return jobId;
  }

  public String getPath() {
    return path;
  }

  /**
   * safety check against the record already being there.
   * @param create DSLContext to use
   */
  public void checkFetchRecordState(DSLContext create) {
    long timestampLong = Long.parseLong(getTimestamp());
    Result<?>
        result =
        create.select().from(FETCHRECORDS).where(FETCHRECORDS.TIMESTAMP.eq(timestampLong))
            .and(FETCHRECORDS.ORGANIZATION_ID.eq(
                Long.parseLong(getOrganizationId()))).and(FETCHRECORDS.NAME.eq(getName())).fetch();
    checkState(result.isEmpty(), "already have fetchrecords for (org, timestamp, name). aborting");
  }

  /**
   * update the DB with this FR.
   * @param create DSLContext to use
   * @param status FetchRecord status.
   */
  public void update(DSLContext create, Optional<String> status) {
    checkNotNull(status);
    checkFetchRecordState(create);
    long timestampLong = Long.parseLong(getTimestamp());
    FetchrecordsRecord frr = create.newRecord(FETCHRECORDS);
    frr.setOrganizationId(Long.parseLong(getOrganizationId()));
    frr.setName(getName());
    frr.setTimestamp(timestampLong);
    frr.setWatermark(timestampLong);
    frr.setType("s3");
    frr.setStatus(status.orElse(DEFAULT_STATUS));
    frr.setName(getName());
    frr.setCreatedAt(new Timestamp(timestampLong));
    frr.setUpdatedAt(new Timestamp(timestampLong));
    frr.setEntityType(getEntityType());
    frr.setJobId(getJobId());
    frr.setChannelName(getChannelName());
    frr.setPath(getPath());
    frr.store();
  }
}
