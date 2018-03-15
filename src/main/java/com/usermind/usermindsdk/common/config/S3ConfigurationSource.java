package com.usermind.usermindsdk.common.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.ACCESS_KEY_PROPERTY;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.SECRET_KEY_PROPERTY;
import static com.usermind.usermindsdk.common.config.BaseEc2ConfigurationSource.TOKEN_PROPERTY;
import static java.nio.charset.StandardCharsets.UTF_8;

public class S3ConfigurationSource implements ConfigurationSource {

  public static final String BUCKET_NAME_PROPERTY = "configSource.s3.bucketName";
  public static final String OBJECT_KEY_PROPERTY = "configSource.s3.objectKey";

  private final String accessKey;
  private final String secretKey;
  private final String sessionToken;
  private final String bucketName;
  private final String objectKey;

  /**
   * Creates new {@code S3ConfigurationSource}.
   * @param roleConfigurationSource configuration source used to pull AWS credentials
   * @param userDataConfigurationSource configuration source used to pull S3 properties
   */
  public S3ConfigurationSource(Ec2InstanceRoleConfigurationSource roleConfigurationSource,
      Ec2InstanceUserDataConfigurationSource userDataConfigurationSource) {

    checkNotNull(roleConfigurationSource);
    checkNotNull(userDataConfigurationSource);

    Configuration roleConfiguration = roleConfigurationSource.load();

    accessKey = roleConfiguration.getString(ACCESS_KEY_PROPERTY);
    secretKey = roleConfiguration.getString(SECRET_KEY_PROPERTY);
    sessionToken = roleConfiguration.getString(TOKEN_PROPERTY);

    Configuration userDataConfiguration = userDataConfigurationSource.load();

    bucketName = userDataConfiguration.getString(BUCKET_NAME_PROPERTY);
    objectKey = userDataConfiguration.getString(OBJECT_KEY_PROPERTY);
  }

  @Override
  public Configuration load() {
    AmazonS3 amazonS3Client = getAmazonS3Client(accessKey, secretKey, sessionToken);
    GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, objectKey);
    S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

    String configurationJson;
    try (InputStream s3ObjectInputStream = s3Object.getObjectContent()) {
      configurationJson = CharStreams.toString(new InputStreamReader(s3ObjectInputStream, UTF_8));
    } catch (IOException exception) {
      throw new RuntimeException("Failed to read configuration JSON from S3 object", exception);
    }

    return ConfigurationBuilder.createConfiguration(configurationJson);
  }

  AmazonS3 getAmazonS3Client(String accessKey, String secretKey, String sessionToken) {
    AWSCredentials awsCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
    return new AmazonS3Client(awsCredentials);
  }
}
