package com.usermind.usermindsdk.common.config;

import com.amazonaws.services.kms.AWSKMS;
import com.google.common.annotations.VisibleForTesting;
import com.usermind.usermindsdk.common.crypto.ConfigurationCrypto;
import com.usermind.usermindsdk.common.crypto.ConfigurationCryptoImplV2;
import com.usermind.usermindsdk.common.support.dry.aws.AwsServiceClientBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * SecureConfigurationBuilderSource.
 */
public class SecuredConfigurationBuilderSource implements ConfigurationSource {

  ConfigurationCrypto crypto;

  /**
   * Creates new {@code SecureConfigurationBuilderSource}.
   *
   * @param kmsClientConfiguration must provide KMS client configuration in the format acceptable by
   *                               {@code AwsServiceClientBuilder}
   */
  public SecuredConfigurationBuilderSource(Configuration kmsClientConfiguration) {
    this(defaultCrypto(kmsClientConfiguration));
  }

  private static ConfigurationCrypto defaultCrypto(Configuration kmsClientConfiguration) {
    checkArgument(nonNull(kmsClientConfiguration));
    kmsClientConfiguration.checkPathExists("defaultKeyId");

    return new ConfigurationCryptoImplV2(
      createAwsKmsClient(kmsClientConfiguration),
      kmsClientConfiguration.getString("defaultKeyId")
    );
  }

  @VisibleForTesting
  SecuredConfigurationBuilderSource(ConfigurationCrypto crypto) {
    this.crypto = crypto;
  }

  @Override
  public Configuration load() {
    throw new UnsupportedOperationException(
        "you cannot load configs via the SecureConfigurationBuilderSource");
  }

  /**
   * build an encrypted configuration from the provided config.
   *
   * @param configuration provided
   * @return encrypted config
   */
  public Configuration build(Configuration configuration) {
    return crypto.encrypt(configuration);
  }

  /*
  These methods are used for testing purposes
 */
  private static AWSKMS createAwsKmsClient(Configuration kmsClientConfiguration) {
    return new AwsServiceClientBuilder(kmsClientConfiguration).getAwsKmsClient();
  }
}
