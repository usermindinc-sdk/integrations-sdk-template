package com.usermind.usermindsdk.common.stream.reader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.services.kinesis.model.DescribeStreamRequest;
import com.amazonaws.services.kinesis.model.LimitExceededException;

/**
 * By doing this we are extending the internal AWS SDK retries. Amazon Kinesis client retries all
 * needed failures except the {@code LimitExceededException} on {@code DescribeStreamRequest}.
 */
public class KinesisStreamRetryCondition
    extends PredefinedRetryPolicies.SDKDefaultRetryCondition {

  @Override
  public boolean shouldRetry(AmazonWebServiceRequest originalRequest,
                             AmazonClientException exception, int retriesAttempted) {

    return super.shouldRetry(originalRequest, exception, retriesAttempted)
        || isLimitExceededOnDescribeStreamRequest(originalRequest, exception);
  }

  private boolean isLimitExceededOnDescribeStreamRequest(AmazonWebServiceRequest originalRequest,
      AmazonClientException exception) {

    return originalRequest instanceof DescribeStreamRequest
        && exception instanceof LimitExceededException;
  }
}
