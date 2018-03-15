package com.usermind.usermindsdk.common.stream.reader;

import com.amazonaws.services.kinesis.model.GetRecordsRequest;

/**
 * {@code GetRecordsRequest} extension aware of its shard id.
 */
public class ShardAwareGetRecordsRequest extends GetRecordsRequest {

  private String shardId;

  public String getShardId() {
    return shardId;
  }

  public ShardAwareGetRecordsRequest withShardId(String shardId) {
    this.shardId = shardId;
    return this;
  }

  @Override
  public ShardAwareGetRecordsRequest withShardIterator(String shardIterator) {
    return (ShardAwareGetRecordsRequest) super.withShardIterator(shardIterator);
  }

  @Override
  public ShardAwareGetRecordsRequest withLimit(Integer limit) {
    return (ShardAwareGetRecordsRequest) super.withLimit(limit);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }

    ShardAwareGetRecordsRequest that = (ShardAwareGetRecordsRequest) obj;

    return shardId != null ? shardId.equals(that.shardId) : that.shardId == null;

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (shardId != null ? shardId.hashCode() : 0);
    return result;
  }
}
