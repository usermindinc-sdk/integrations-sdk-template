package com.usermind.usermindsdk.common.support.dry.aws;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.ACCESS_KEY_PROPERTY;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.SECRET_KEY_PROPERTY;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.TOKEN_PROPERTY;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.S3ClientOptions;
import com.google.common.base.Strings;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.Ec2InstanceRoleConfigurationSource;

/**
 * AWS Service Clients builder.
 */
public class AwsServiceClientBuilder {

  // TODO: use smaller max retries value ?
  private static final RetryPolicy DEFAULT_RETRY_POLICY = new RetryPolicy(null, null, 25, true);
  private static final String DEFAULT_EC2_ROLE_ACCOUNT = "022353409608";
  private static final String DEFAULT_SESSION_NAME = "integrations-worker-lib";

  private final Configuration configuration;
  private final RetryPolicy retryPolicy;

  /**
   * AWS Service Client Builder.
   *
   * @param prefix prefix in the provided configuration
   * @param configuration configuration to use
   */
  public AwsServiceClientBuilder(String prefix, Configuration configuration) {
    this(prefix, configuration, DEFAULT_RETRY_POLICY);
  }

  /**
   * AWS Service Client Builder.
   *
   * @param prefix prefix in the provided configuration
   * @param configuration configuration to use
   * @param retryPolicy SDK default retry policy override
   */
  public AwsServiceClientBuilder(String prefix, Configuration configuration,
                                 RetryPolicy retryPolicy) {

    checkArgument(!Strings.isNullOrEmpty(prefix));
    checkNotNull(configuration);
    this.configuration = configuration.atPath(prefix);
    this.retryPolicy = checkNotNull(retryPolicy);
  }

  /**
   * AWS Service Client Builder.
   *
   * @param configuration configuration to use
   */
  public AwsServiceClientBuilder(Configuration configuration) {
    this(configuration, DEFAULT_RETRY_POLICY);
  }

  /**
   * AWS Service Client Builder.
   *
   * @param configuration configuration to use
   * @param retryPolicy SDK default retry policy override
   */
  public AwsServiceClientBuilder(Configuration configuration, RetryPolicy retryPolicy) {
    this.configuration = checkNotNull(configuration);
    this.retryPolicy = checkNotNull(retryPolicy);
  }

  private AWSCredentialsProvider getCredentials() {
    Configuration credentialsConfig;

    if (configuration.hasPath("ec2Role")) {
      String role = configuration.getString("ec2Role");
      String account = configuration.getString("awsAccount", DEFAULT_EC2_ROLE_ACCOUNT);
      String sessionName = configuration.getString("sessionName", DEFAULT_SESSION_NAME);

      String roleArn = String.format("arn:aws:iam::%s:role/%s", account, role);
      return new STSAssumeRoleSessionCredentialsProvider(roleArn, sessionName);
    } else if (configuration.hasPath("credentials")) {
      configuration.checkPathExists("credentials");
      credentialsConfig = configuration.atPath("credentials");
    } else {
      return new DefaultAWSCredentialsProviderChain();
    }

    credentialsConfig.checkPathExists(ACCESS_KEY_PROPERTY);
    credentialsConfig.checkPathExists(SECRET_KEY_PROPERTY);
    String accessKey = credentialsConfig.getString(ACCESS_KEY_PROPERTY);
    String secretKey = credentialsConfig.getString(SECRET_KEY_PROPERTY);

    String token = null;
    if (credentialsConfig.hasPath(TOKEN_PROPERTY)) {
      token = credentialsConfig.getString(TOKEN_PROPERTY);
    }

    AWSCredentials credentials = null;
    if (token != null) {
      credentials = new BasicSessionCredentials(accessKey, secretKey, token);
    } else {
      credentials = new BasicAWSCredentials(accessKey, secretKey);
    }
    return new StaticCredentialsProvider(credentials);
  }

  private ClientConfiguration getClientConfiguration() {
    ClientConfiguration clientConfiguration = new ClientConfiguration();

    configuration.checkPathExists("socketTimeout");
    clientConfiguration.setSocketTimeout(configuration.getInt("socketTimeout"));

    clientConfiguration.setRetryPolicy(retryPolicy);
    clientConfiguration.setMaxErrorRetry(retryPolicy.getMaxErrorRetry());

    return clientConfiguration;
  }

  private void setupAmazonWebServiceClient(AmazonWebServiceClient client) {
    configuration.checkPathExists("endpoint");
    client.setEndpoint(configuration.getString("endpoint"));
  }

  /**
   * builds a kinesis client from the provided config.
   *
   * @return the kinesis client
   */
  public AmazonKinesisClient getKinesisClient() {
    AmazonKinesisClient amazonKinesisClient =
        new AmazonKinesisClient(getCredentials(), getClientConfiguration());
    setupAmazonWebServiceClient(amazonKinesisClient);
    return amazonKinesisClient;
  }

  /**
   * builds a swf client from the provided config.
   *
   * @return the swf client
   */
  public AmazonSimpleWorkflowClient getSimpleWorkflowClient() {
    AmazonSimpleWorkflowClient amazonSimpleWorkflowClient =
        new AmazonSimpleWorkflowClient(getCredentials(), getClientConfiguration());
    setupAmazonWebServiceClient(amazonSimpleWorkflowClient);
    return amazonSimpleWorkflowClient;
  }

  /**
   * build a ddb client from the provided config.
   *
   * @return the ddb client
   */
  public AmazonDynamoDBClient getDynamoDbClient() {
    AmazonDynamoDBClient amazonDynamoDbClient =
        new AmazonDynamoDBClient(getCredentials(), getClientConfiguration());
    setupAmazonWebServiceClient(amazonDynamoDbClient);
    return amazonDynamoDbClient;
  }

  /**
   * build a s3 client from the provided config.
   *
   * @return the s3 client
   */
  public AmazonS3Client getS3Client() {
    AmazonS3Client amazonS3Client =
        new AmazonS3Client(getCredentials(), getClientConfiguration());
    setupAmazonWebServiceClient(amazonS3Client);

    if (configuration.hasPath("pathStyle")) {
      S3ClientOptions options = S3ClientOptions.builder()
          .setPathStyleAccess(configuration.getBoolean("pathStyle"))
          // Primary use case is for using fake-s3, which currently fails when chunked encoding
          // is enabled: https://github.com/jubos/fake-s3/issues/164
          .disableChunkedEncoding()
          .build();
      amazonS3Client.setS3ClientOptions(options);
    }

    // In the dev environment we want to automatically create s3 buckets when the docker stack
    // comes up.  The config-docker.json should have "createBucket" as true for s3 and s3writer
    if (configuration.getBoolean("createBucket", false)) {
      String bucketName = configuration.getString("bucket");
      if (!amazonS3Client.doesBucketExist(bucketName)) {
        amazonS3Client.createBucket(bucketName);
      }
    }

    return amazonS3Client;
  }

  /**
   * build a kms client from provided config.
   * @return the kms client
   */
  public AWSKMSClient getAwsKmsClient() {
    AWSKMSClient awsKmsClient = new AWSKMSClient(getCredentials(), getClientConfiguration());
    setupAmazonWebServiceClient(awsKmsClient);
    return awsKmsClient;
  }
}
