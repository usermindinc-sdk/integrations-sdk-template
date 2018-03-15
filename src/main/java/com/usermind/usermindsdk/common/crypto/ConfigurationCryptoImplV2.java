package com.usermind.usermindsdk.common.crypto;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyRequest;
import com.amazonaws.services.kms.model.GenerateDataKeyResult;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

import static com.amazonaws.services.kms.model.DataKeySpec.AES_256;
import static java.nio.ByteBuffer.wrap;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class ConfigurationCryptoImplV2 implements ConfigurationCrypto {

  private static final String VERSION_PROPERTY = "encryptionVersion";
  private static final String VERSION_VALUE = "2";
  private static final String SYMMETRIC_KEY_PROPERTY = "encryptedSymmetricKey";
  private static final String CONFIGURATION_PROPERTY = "encryptedConfiguration";
  private static final String SYMMETRIC_KEY_GENERATION_ALGORITHM = "AES";
  private static final String SYMMETRIC_ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding";
  public static final int SYMMETRIC_KEY_SIZE = 256;
  private static final int SYMMETRIC_BLOCK_SIZE = 128;
  private static final int GCM_NONCE_BYTES = 12;

  private final AWSKMS awsKmsClient;
  private final String keyId;
  private final SecureRandom random;
  private final Base64.Encoder encoder;
  private final Base64.Decoder decoder;
  private final LoadingCache<String, byte[]> decryptionKeys;

  private GenerateDataKeyResult cachedKey;

  public ConfigurationCryptoImplV2(AWSKMS awsKmsClient, String keyId) {
    this.awsKmsClient = awsKmsClient;
    this.keyId = keyId;
    this.random = new SecureRandom();
    this.encoder = Base64.getEncoder();
    this.decoder = Base64.getDecoder();
    this.decryptionKeys = CacheBuilder.<String, byte[]>newBuilder()
      .initialCapacity(10)
      .maximumSize(100)
      .build(CacheLoader.from(this::decryptKey));
  }

  private byte[] decryptKey(String key) {
    DecryptRequest request = new DecryptRequest().withCiphertextBlob(wrap(decoder.decode(key.getBytes(UTF_8))));
    return awsKmsClient.decrypt(request).getPlaintext().array();
  }

  @Override
  public Configuration encrypt(Configuration original) {
    try {
      GenerateDataKeyResult keyResult = getCachedKey();

      byte[] encrypted = encrypt(keyResult.getPlaintext().array(), original.toString().getBytes(UTF_8));

      return new ConfigurationBuilder()
        .put(VERSION_PROPERTY, VERSION_VALUE)
        .put(SYMMETRIC_KEY_PROPERTY, new String(encoder.encode(keyResult.getCiphertextBlob().array()), UTF_8))
        .put(CONFIGURATION_PROPERTY, new String(encoder.encode(encrypted), UTF_8))
        .toConfiguration();

    } catch (GeneralSecurityException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public Configuration decrypt(Configuration encrypted) {
    encrypted.checkPathExists(SYMMETRIC_KEY_PROPERTY);
    encrypted.checkPathExists(CONFIGURATION_PROPERTY);
    byte[] dataKey = decryptionKeys.getUnchecked(encrypted.getString(SYMMETRIC_KEY_PROPERTY));

    try {
      byte[] plaintext = decrypt(dataKey, decoder.decode(encrypted.getString(CONFIGURATION_PROPERTY).getBytes(UTF_8)));
      return ConfigurationBuilder.createConfiguration(new String(plaintext, UTF_8));
    } catch (GeneralSecurityException ex) {
      throw new RuntimeException(ex);
    }
  }

  private synchronized GenerateDataKeyResult getCachedKey() {
    if (cachedKey == null) {
      GenerateDataKeyRequest request = new GenerateDataKeyRequest().withKeyId(keyId).withKeySpec(AES_256);
      cachedKey = awsKmsClient.generateDataKey(request);
    }

    return cachedKey;
  }

  private byte[] encrypt(byte[] key, byte[] data) throws GeneralSecurityException {
    SecretKey secretKey = new SecretKeySpec(key, SYMMETRIC_KEY_GENERATION_ALGORITHM);
    byte[] iv = new byte[GCM_NONCE_BYTES];
    random.nextBytes(iv);

    Cipher cipher = Cipher.getInstance(SYMMETRIC_ENCRYPTION_ALGORITHM);
    cipher.init(ENCRYPT_MODE, secretKey, new GCMParameterSpec(SYMMETRIC_BLOCK_SIZE, iv));
    byte[] encrypted = cipher.doFinal(data);
    byte[] cipherText = new byte[iv.length + encrypted.length];
    System.arraycopy(iv, 0, cipherText, 0, iv.length);
    System.arraycopy(encrypted, 0, cipherText, iv.length, encrypted.length);
    return cipherText;
  }

  private byte[] decrypt(byte[] key, byte[] data) throws GeneralSecurityException {
    SecretKey secretKey = new SecretKeySpec(key, SYMMETRIC_KEY_GENERATION_ALGORITHM);
    Cipher cipher = Cipher.getInstance(SYMMETRIC_ENCRYPTION_ALGORITHM);
    cipher.init(DECRYPT_MODE, secretKey, new GCMParameterSpec(SYMMETRIC_BLOCK_SIZE, data, 0, GCM_NONCE_BYTES));
    return cipher.doFinal(data, GCM_NONCE_BYTES, data.length - GCM_NONCE_BYTES);
  }

}
