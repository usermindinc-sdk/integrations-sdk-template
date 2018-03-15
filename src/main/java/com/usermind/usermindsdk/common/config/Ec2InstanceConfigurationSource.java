package com.usermind.usermindsdk.common.config;

import java.util.Optional;

/**
 * Reads and decrypts the configuration from an EC2 instance.
 *
 * <p>integrates the other EC2Instance* Configuration sources to produce one configuration
 */
public class Ec2InstanceConfigurationSource implements ConfigurationSource {

  /* path to look for role */
  public static final String DEFAULT_INSTANCE_ROLE_PATH = "iamRole";

  @Override
  public Configuration load() {
    Ec2InstanceUserDataConfigurationSource ec2InstanceUserDataConfigurationSource =
        createEc2InstanceUserDataConfigurationSource();

    String iamRole = ec2InstanceUserDataConfigurationSource.load()
        .getString(DEFAULT_INSTANCE_ROLE_PATH);

    Ec2InstanceRoleConfigurationSource ec2InstanceRoleConfigurationSource =
        createEc2InstanceRoleConfigurationSource(iamRole);

    S3ConfigurationSource s3ConfigurationSource = createS3ConfigurationSource(
        ec2InstanceRoleConfigurationSource, ec2InstanceUserDataConfigurationSource);

    KmsEncryptionConfigurationsSource kmsEncryptionConfigurationsSource =
        createKmsEncryptionConfigurationsSource(ec2InstanceRoleConfigurationSource,
            s3ConfigurationSource);

    EnvelopeEncryptionConfigurationSource envelopeEncryptionConfigurationSource =
        createEnvelopeEncryptionConfigurationSource(kmsEncryptionConfigurationsSource,
            s3ConfigurationSource);

    return envelopeEncryptionConfigurationSource.load();
  }

  /*
    These methods are used for testing purposes
   */
  Ec2InstanceUserDataConfigurationSource createEc2InstanceUserDataConfigurationSource() {
    return new Ec2InstanceUserDataConfigurationSource(Optional.empty());
  }

  Ec2InstanceRoleConfigurationSource createEc2InstanceRoleConfigurationSource(String iamRole) {
    return new Ec2InstanceRoleConfigurationSource(iamRole);
  }

  S3ConfigurationSource createS3ConfigurationSource(
      Ec2InstanceRoleConfigurationSource roleConfigurationSource,
      Ec2InstanceUserDataConfigurationSource userDataConfigurationSource) {

    return new S3ConfigurationSource(roleConfigurationSource, userDataConfigurationSource);
  }

  KmsEncryptionConfigurationsSource createKmsEncryptionConfigurationsSource(
      Ec2InstanceRoleConfigurationSource iamKeysSource,
      S3ConfigurationSource s3ConfigurationSource) {

    return new KmsEncryptionConfigurationsSource(iamKeysSource, s3ConfigurationSource);
  }

  EnvelopeEncryptionConfigurationSource createEnvelopeEncryptionConfigurationSource(
      ConfigurationSource decryptedMasterKeySource,
      ConfigurationSource encryptedConfigurationSource) {

    return new EnvelopeEncryptionConfigurationSource(decryptedMasterKeySource,
        encryptedConfigurationSource);
  }
}
