package com.usermind.usermindsdk.common.config;

import com.amazonaws.services.kms.AWSKMS;
import com.google.common.annotations.VisibleForTesting;
import com.usermind.usermindsdk.common.crypto.ConfigurationCrypto;
import com.usermind.usermindsdk.common.crypto.ConfigurationCryptoImplV1;
import com.usermind.usermindsdk.common.crypto.ConfigurationCryptoImplV2;
import com.usermind.usermindsdk.common.support.dry.aws.AwsServiceClientBuilder;


import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * Configuration source providing configuration object by decrypting an encrypted configuration.
 * First this source decrypts a master key using KMS client and then passes decrypted master key and
 * encrypted configuration to the {@code EnvelopeEncryptionConfigurationSource}.
 */
public class SecuredConfigurationSource implements ConfigurationSource {

  private final ConfigurationSource encryptedConfigurationSource;
  private final Map<String, ConfigurationCrypto> cryptoVersions;

  /**
   * Creates new {@code SecuredConfigurationSource}.
   * @param kmsClientConfiguration must provide KMS client configuration in the format acceptable by
   *                               {@code AwsServiceClientBuilder}
   * @param encryptedConfigurationSource must provide properties "encryptedMasterKey",
   *                                     "encryptedSymmetricKey" and "encryptedConfiguration"
   */
  public SecuredConfigurationSource(Configuration kmsClientConfiguration,
      ConfigurationSource encryptedConfigurationSource) {
    this(encryptedConfigurationSource, createCryptoVersions(createAwsKmsClient(kmsClientConfiguration)));
  }

  @VisibleForTesting
  SecuredConfigurationSource(ConfigurationSource encryptedConfigurationSource,
                             Map<String, ConfigurationCrypto> cryptoVersions) {
    checkArgument(nonNull(encryptedConfigurationSource));
    checkArgument(nonNull(cryptoVersions));
    checkArgument(!cryptoVersions.isEmpty());

    this.encryptedConfigurationSource = encryptedConfigurationSource;
    this.cryptoVersions = cryptoVersions;
  }

  @Override
  public Configuration load() {
    Configuration encryptedConfiguration = encryptedConfigurationSource.load();
    String version = encryptedConfiguration.getString("encryptionVersion", null);
    return cryptoVersions.get(version).decrypt(encryptedConfiguration);
  }

  private static AWSKMS createAwsKmsClient(Configuration kmsClientConfiguration) {
    checkArgument(nonNull(kmsClientConfiguration));
    return new AwsServiceClientBuilder(kmsClientConfiguration).getAwsKmsClient();
  }

  private static Map<String, ConfigurationCrypto> createCryptoVersions(AWSKMS awsKmsClient) {
    Map<String, ConfigurationCrypto> versions = new HashMap<>();
    versions.put(null, new ConfigurationCryptoImplV1(awsKmsClient, null));
    versions.put("2", new ConfigurationCryptoImplV2(awsKmsClient, null));
    return versions;
  }
}
