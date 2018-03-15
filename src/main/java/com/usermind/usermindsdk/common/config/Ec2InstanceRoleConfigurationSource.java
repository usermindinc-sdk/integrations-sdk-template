package com.usermind.usermindsdk.common.config;

import com.google.common.base.Preconditions;

/**
 * Read role from EC2 Instance Metadata.
 *
 * <p>
 * The instance metadata is available from within a running instance only, i.e. it's not available
 * from outside of the instance.
 *
 * @see <a href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html">Instance
 *      Metadata and User Data</a>
 */
public class Ec2InstanceRoleConfigurationSource extends BaseEc2ConfigurationSource {

  public static final String EC2_METADATA_CREDENTIALS_PATH =
      "/latest/meta-data/iam/security-credentials/";

  private final String iamRole;

  /**
   * iam security credentials property containing secret key id.
   */
  public static final String EC2_METADATA_SECRET_KEY_PROPERTY = "SecretAccessKey";

  /**
   * iam security credentials property containing access key id.
   */
  public static final String EC2_METADATA_ACCESS_KEY_PROPERTY = "AccessKeyId";

  /**
   * iam security credentials property containing token.
   */
  public static final String EC2_METADATA_TOKEN_PROPERTY = "Token";

  public Ec2InstanceRoleConfigurationSource(String iamRole) {
    this.iamRole = Preconditions.checkNotNull(iamRole);
  }

  @Override
  public Configuration load() {
    Configuration securityCredentialsConfig = getSecurityCredentialsConfig(iamRole);
    String accessKey = securityCredentialsConfig.getString(EC2_METADATA_ACCESS_KEY_PROPERTY);
    String secretKey = securityCredentialsConfig.getString(EC2_METADATA_SECRET_KEY_PROPERTY);
    String token = securityCredentialsConfig.getString(EC2_METADATA_TOKEN_PROPERTY);

    ConfigurationBuilder builder = new ConfigurationBuilder()
        .put(ACCESS_KEY_PROPERTY, accessKey)
        .put(SECRET_KEY_PROPERTY, secretKey)
        .put(TOKEN_PROPERTY, token);

    return builder.toConfiguration();
  }

  private Configuration getSecurityCredentialsConfig(String role) {
    String securityCredentialsJson = getInstanceMetadata(EC2_METADATA_CREDENTIALS_PATH + role);
    return ConfigurationBuilder.createConfiguration(securityCredentialsJson);
  }

}
