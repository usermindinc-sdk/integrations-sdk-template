package com.usermind.usermindsdk.common.config;

import com.google.common.base.Preconditions;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Configuration source providing configuration object by decrypting an encrypted configuration
 * using the envelope encryption.
 */
public class EnvelopeEncryptionConfigurationSource implements ConfigurationSource {

  /**
   * User data configuration property containing encrypted configuration.
   */
  public static final String USER_DATA_ENCRYPTED_CONFIGURATION_PROPERTY = "encryptedConfiguration";
  public static final String USER_DATA_ENCRYPTED_SYMMETRIC_KEY_PROPERTY = "encryptedSymmetricKey";

  private static final String SYMMETRIC_ENCRYPTION_ALGORITHM = "AES";
  private static final String ASSYMETRIC_ENCRYPTION_ALGORITHM = "RSA";
  private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

  private ConfigurationSource decryptedMasterKeySource;
  private ConfigurationSource encryptedConfigSource;

  /**
   * Create a {@link EnvelopeEncryptionConfigurationSource} wired with the configuration sources
   * for decrypted master key and encrypted configuration.
   *
   * @param decryptedMasterKeySource a configuration source containing properties with the
   *        {@link BaseEc2ConfigurationSource#DECRYPTED_MASTER_KEY_PROPERTY}.
   * @param encryptedConfigSource a configuration source containing properties with the
   *        {@link #USER_DATA_ENCRYPTED_CONFIGURATION_PROPERTY}.
   */
  public EnvelopeEncryptionConfigurationSource(ConfigurationSource decryptedMasterKeySource,
      ConfigurationSource encryptedConfigSource) {

    this.decryptedMasterKeySource = Preconditions.checkNotNull(decryptedMasterKeySource);
    this.encryptedConfigSource = Preconditions.checkNotNull(encryptedConfigSource);
  }

  @Override
  public Configuration load() {
    Configuration decryptedMasterKeyConfig = decryptedMasterKeySource.load();
    PrivateKey masterRsaKey = getPrivateKey(decryptedMasterKeyConfig
        .getString(BaseEc2ConfigurationSource.DECRYPTED_MASTER_KEY_PROPERTY));

    // base64-encoded ciphertext:
    Configuration encryptedConfig = encryptedConfigSource.load();

    // get encrypted symmetric key:
    String encryptedSymmetricKey =
        encryptedConfig.getString(USER_DATA_ENCRYPTED_SYMMETRIC_KEY_PROPERTY);

    // decrypt symmetric key using masterRsaKey
    String base64EncodedSymmetricKey = decryptWithRsaKey(encryptedSymmetricKey, masterRsaKey);

    // decrypt configuration with symmetric key
    SecretKey symmetricKey = getSymmetricKey(base64EncodedSymmetricKey);
    String encryptedData = encryptedConfig.getString(USER_DATA_ENCRYPTED_CONFIGURATION_PROPERTY);
    String decryptedData = decryptWithSymmetricKey(encryptedData, symmetricKey);

    return ConfigurationBuilder.createConfiguration(decryptedData);
  }

  private PrivateKey getPrivateKey(String base64EncodedPrivateKey) {
    byte[] keyBytes = Base64.getDecoder().decode(base64EncodedPrivateKey);

    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

    try {
      KeyFactory kf = KeyFactory.getInstance(ASSYMETRIC_ENCRYPTION_ALGORITHM);
      return kf.generatePrivate(spec);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException("Can not decode private key.", e);
    }
  }

  private SecretKey getSymmetricKey(String base64EncodedSymmetricKey) {
    byte[] keyBytes = Base64.getDecoder().decode(base64EncodedSymmetricKey);
    return new SecretKeySpec(keyBytes, SYMMETRIC_ENCRYPTION_ALGORITHM);
  }

  /**
   * @param base64EncodedCiphertext base64 encoded ciphertext.
   * @param privateKey RSA private key to decrypt ciphertext.
   *
   * @return decrypted ciphertext.
   */
  private String decryptWithRsaKey(String base64EncodedCiphertext, PrivateKey privateKey) {
    return decrypt(TRANSFORMATION, base64EncodedCiphertext, privateKey);
  }


  /**
   * @param base64EncodedCiphertext base64 encoded ciphertext.
   * @param secretKey symmetric key to decrypt ciphertext.
   *
   * @return decrypted ciphertext.
   */
  private String decryptWithSymmetricKey(String base64EncodedCiphertext, SecretKey secretKey) {
    return decrypt(SYMMETRIC_ENCRYPTION_ALGORITHM, base64EncodedCiphertext, secretKey);
  }

  private String decrypt(String algorithm, String base64EncodedCipherText, Key key) {
    byte[] cipherText = Base64.getDecoder().decode(base64EncodedCipherText.getBytes(UTF_8));
    byte[] plaintext = decrypt(algorithm, cipherText, key);
    return new String(plaintext, UTF_8);
  }

  private byte[] decrypt(String algorithm, byte[] cipherText, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(algorithm);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return cipher.doFinal(cipherText);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException("Cannot decrypt base64 encoded ciphertext.", e);
    }
  }
}
