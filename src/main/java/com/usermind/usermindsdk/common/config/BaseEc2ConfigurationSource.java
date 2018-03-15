package com.usermind.usermindsdk.common.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.EC2MetadataUtils;

/**
 * Base class for the configuration sources reading metadata and/or user data from EC2 instance.
 *
 * @see <a href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html">
 *      Instance Metadata and User Data</a>
 */
public abstract class BaseEc2ConfigurationSource implements ConfigurationSource {

  /**
   * Configuration property containing access key retrieved from ami metadata.
   */
  public static final String ACCESS_KEY_PROPERTY = "accessKey";

  /**
   * Configuration property containing secret key retrieved from ami metadata.
   */
  public static final String SECRET_KEY_PROPERTY = "secretKey";

  /**
   * Configuration property containing token retrieved from ami metadata.
   */
  public static final String TOKEN_PROPERTY = "token";

  /**
   * Configuration property containing decrypted master key.
   */
  public static final String DECRYPTED_MASTER_KEY_PROPERTY = "masterKey";
  public static final String AWS_AVAILABILITY_ZONE_PATH =
      "/latest/meta-data/placement/availability-zone/";

  /**
   * Exponential retry is supported (number of tries is 3).
   *
   * @param metadataRelativePath relative instance metadata endpoint.
   * @return response of the instance metadata endpoint.
   */
  protected String getInstanceMetadata(String metadataRelativePath) {
    String metadata = null;
    try {
      metadata = EC2MetadataUtils.getData(metadataRelativePath);
    } catch (AmazonClientException e) {
      throw new RuntimeException(getErrorMessage(metadataRelativePath), e);
    }
    if (metadata == null) {
      throw new RuntimeException(getErrorMessage(metadataRelativePath));
    }
    return metadata;
  }

  private String getErrorMessage(String metadataRelativePath) {
    return "Cannot get \"" + metadataRelativePath
        + "\" attribute from the current EC2 instance metadata";
  }
}
