package com.usermind.usermindsdk.common.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.google.common.base.Preconditions;

import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Configuration source providing configuration object by decrypting an encrypted master key using
 * AWS KMS service.
 *
 * <p>
 * The resulting configuration contains decrypted master key under the
 * {@link BaseEc2ConfigurationSource#DECRYPTED_MASTER_KEY_PROPERTY} path.
 */
public class KmsEncryptionConfigurationsSource extends BaseEc2ConfigurationSource {

  /**
   * User data configuration property containing encrypted master key.
   */
  public static final String USER_DATA_ENCRYPTED_MASTER_KEY_PROPERTY = "encryptedMasterKey";

  public static final String KMS_ENDPOINT_TEMPLATE = "https://kms.{0}.amazonaws.com";

  /**
   * The source for IAM accessKey and secretKey.
   */
  private final Ec2InstanceRoleConfigurationSource iamKeysSource;

  /**
   * The source for IAM accessKey and secretKey.
   */
  private final S3ConfigurationSource s3ConfigurationsSource;

  /**
   * Create a {@link KmsEncryptionConfigurationsSource} wired with the configuration sources for iam
   * security credentials and encrypted data.
   *
   * @param iamKeysSource a configuration source containing properties with the
   *        {@link BaseEc2ConfigurationSource#ACCESS_KEY_PROPERTY} and
   *        {@link BaseEc2ConfigurationSource#SECRET_KEY_PROPERTY} keys.
   * @param s3ConfigurationsSource a configuration source containing property with the
   *        {@link #USER_DATA_ENCRYPTED_MASTER_KEY_PROPERTY} key.
   */
  public KmsEncryptionConfigurationsSource(Ec2InstanceRoleConfigurationSource iamKeysSource,
      S3ConfigurationSource s3ConfigurationsSource) {
    this.iamKeysSource = Preconditions.checkNotNull(iamKeysSource);
    this.s3ConfigurationsSource = Preconditions.checkNotNull(s3ConfigurationsSource);
  }

  @Override
  public Configuration load() {
    // get IAM keys
    Configuration iamKeysConfiguration = iamKeysSource.load();
    String accessKey = iamKeysConfiguration.getString(ACCESS_KEY_PROPERTY);
    String secretKey = iamKeysConfiguration.getString(SECRET_KEY_PROPERTY);
    String token = iamKeysConfiguration.getString(TOKEN_PROPERTY);

    // get the value to be decrypted
    Configuration encryptedConfiguration = s3ConfigurationsSource.load();
    String base64EncodedKmsEncryptedMasterKey =
        encryptedConfiguration.getString(USER_DATA_ENCRYPTED_MASTER_KEY_PROPERTY);

    // call KMS and get decrypted key
    String decryptedKey = decrypt(accessKey, secretKey, token, base64EncodedKmsEncryptedMasterKey);

    return new ConfigurationBuilder().put(DECRYPTED_MASTER_KEY_PROPERTY, decryptedKey)
        .toConfiguration();
  }

  // package-private method, so that it can be mocked in tests
  AWSKMS getKmsClient(String accessKey, String secretKey, String token) {
    AWSCredentials awsCredentials = new BasicSessionCredentials(accessKey, secretKey, token);
    return new AWSKMSClient(awsCredentials);
  }

  private String decrypt(String accessKey, String secretKey, String token,
      String base64EncodedCiphertext) {
    AWSKMS kms = getKmsClient(accessKey, secretKey, token);
    String kmsEndpoint = MessageFormat.format(KMS_ENDPOINT_TEMPLATE, getRegion());
    kms.setEndpoint(kmsEndpoint);

    // the ciphertext is base64 encoded, so decode it before sending to KMS
    ByteBuffer ciphertextBlob = base64Decode(base64EncodedCiphertext);

    // send request to KMS
    DecryptRequest decryptRequest = new DecryptRequest().withCiphertextBlob(ciphertextBlob);
    ByteBuffer decryptionResult = kms.decrypt(decryptRequest).getPlaintext();

    // encode the decrypted key
    String base64EncodedResult = Base64.getEncoder().encodeToString(decryptionResult.array());
    return base64EncodedResult;
  }

  private String getRegion() {
    String availabilityZone = getInstanceMetadata(AWS_AVAILABILITY_ZONE_PATH)
        .replace("\n", "").replace("\r", "");

    // the instance-metadata does not contain the region itself, so we have to derive it from
    // availability zone by removing the last character:
    return availabilityZone.substring(0, availabilityZone.length() - 1);
  }

  private ByteBuffer base64Decode(String str) {
    ByteBuffer blob = ByteBuffer.wrap(str.getBytes(UTF_8));
    return Base64.getDecoder().decode(blob);
  }

}
