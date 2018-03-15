package com.usermind.usermindsdk.common.config;

import java.util.Optional;

/**
 * Read configuration from EC2 userData.
 *
 * <p>
 * The instance metadata are available from within a running instance only, i.e. it's not available
 * from outside of the instance.
 *
 * @see <a href="http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html">Instance
 *      Metadata and User Data</a>
 */
public class Ec2InstanceUserDataConfigurationSource extends BaseEc2ConfigurationSource {

  public static final String EC2_USER_DATA_PATH = "/latest/user-data";

  private final Optional<String> basePath;

  /**
   * The user data may potentially have multiple {iamRole, encryptedKey, encryptedData} objects.
   * This constructor allows specifying the configuration object of interest.
   *
   * @param basePath if specified, point to the subconfig path that hold the {iamRole, encryptedKey,
   *        encryptedData} triple. Otherwise, points to the root of the configuration specified in
   *        user data.
   */
  public Ec2InstanceUserDataConfigurationSource(Optional<String> basePath) {
    this.basePath = basePath;
  }

  @Override
  public Configuration load() {
    Configuration userDataConfig = ConfigurationBuilder.createConfiguration(getUserData());

    if (basePath.isPresent()) {
      return userDataConfig.atPath(basePath.get());
    } else {
      return userDataConfig;
    }
  }

  private String getUserData() {
    return getInstanceMetadata(EC2_USER_DATA_PATH);
  }
}
