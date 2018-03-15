package com.usermind.usermindsdk.common.crypto;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptResult;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ConfigurationCryptoImplV1 implements ConfigurationCrypto {

  private static final String ENCRYPTED_MASTER_KEY_PROPERTY = "encryptedMasterKey";
  private static final String ENCRYPTED_SYMMETRIC_KEY_PROPERTY = "encryptedSymmetricKey";
  private static final String ENCRYPTED_CONFIGURATION_PROPERTY = "encryptedConfiguration";

  private static final String ASYMMETRIC_ENCRYPTION_ALGORITHM = "RSA";
  private static final int ASYMMETRIC_KEY_SIZE = 2048;
  private static final String SYMMETRIC_ENCRYPTION_ALGORITHM = "AES";
  private static final int SYMMETRIC_KEY_SIZE = 128;
  private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

  private final AWSKMS awsKmsClient;
  private final String defaultKeyId;

  public ConfigurationCryptoImplV1(AWSKMS awsKmsClient, String defaultKeyId) {
    this.awsKmsClient = awsKmsClient;
    this.defaultKeyId = defaultKeyId;
  }

  @Override
  public Configuration encrypt(Configuration original) {
    String keyId = original.getString("keyId", defaultKeyId);

    try {
      KeyPair keyPair = generateKeyPair();
      SecretKey symmetricKey = generateSymmetricKey();
      String encryptedMasterKey = kmsEncryptKey(keyId, keyPair);
      String encryptedSymmetricKey = encryptSymmetricKey(keyPair, symmetricKey);
      String encryptedConfiguration = encryptConfig(original, symmetricKey);

      return new ConfigurationBuilder()
        .put(ENCRYPTED_MASTER_KEY_PROPERTY, encryptedMasterKey)
        .put(ENCRYPTED_SYMMETRIC_KEY_PROPERTY, encryptedSymmetricKey)
        .put(ENCRYPTED_CONFIGURATION_PROPERTY, encryptedConfiguration)
        .toConfiguration();

    } catch (GeneralSecurityException gse) {
      throw new RuntimeException(gse);
    }
  }

  @Override
  public Configuration decrypt(Configuration encrypted) {
    encrypted.checkPathExists(ENCRYPTED_MASTER_KEY_PROPERTY);
    encrypted.checkPathExists(ENCRYPTED_SYMMETRIC_KEY_PROPERTY);
    encrypted.checkPathExists(ENCRYPTED_CONFIGURATION_PROPERTY);

    String encryptedMasterKey = encrypted.getString(ENCRYPTED_MASTER_KEY_PROPERTY);
    PrivateKey masterKey = decryptMasterKey(encryptedMasterKey);

    String encryptedKey = encrypted.getString(ENCRYPTED_SYMMETRIC_KEY_PROPERTY);
    String encodedKey = decryptWithRsaKey(encryptedKey, masterKey);

    SecretKey symmetricKey = getSymmetricKey(encodedKey);
    String encryptedData = encrypted.getString(ENCRYPTED_CONFIGURATION_PROPERTY);
    String decryptedData = decryptWithSymmetricKey(encryptedData, symmetricKey);
    return ConfigurationBuilder.createConfiguration(decryptedData);
  }

  // ============= Encryption ==============

  private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ASYMMETRIC_ENCRYPTION_ALGORITHM);
    keyPairGenerator.initialize(ASYMMETRIC_KEY_SIZE);
    return keyPairGenerator.genKeyPair();
  }

  private SecretKey generateSymmetricKey() throws NoSuchAlgorithmException {
    // generate symmetric key:
    KeyGenerator generator = KeyGenerator.getInstance(SYMMETRIC_ENCRYPTION_ALGORITHM);
    generator.init(SYMMETRIC_KEY_SIZE);
    return generator.generateKey();
  }

  private String kmsEncryptKey(String kmsKeyId, KeyPair keyPair) {
    EncryptRequest encryptRequest = new EncryptRequest();
    encryptRequest.setKeyId(kmsKeyId);
    encryptRequest.setPlaintext(ByteBuffer.wrap(keyPair.getPrivate().getEncoded()));
    EncryptResult encryptResult = awsKmsClient.encrypt(encryptRequest);
    return Base64.getEncoder().encodeToString(encryptResult.getCiphertextBlob().array());
  }

  private String encryptSymmetricKey(KeyPair keyPair, SecretKey secretKey)
    throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidKeyException {
    Cipher cipher = getCipher(keyPair);
    return Base64.getEncoder()
      .encodeToString(cipher.doFinal(Base64.getEncoder().encode(secretKey.getEncoded())));
  }

  private String encryptConfig(Configuration config, SecretKey secretKey)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(SYMMETRIC_ENCRYPTION_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return Base64.getEncoder().encodeToString(cipher.doFinal(config.toString().getBytes(UTF_8)));
  }

  private Cipher getCipher(KeyPair keyPair)
    throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
    return cipher;
  }

  // ============= Decryption ==============

  private PrivateKey decryptMasterKey(String encrypted) {
    ByteBuffer ciphertext = ByteBuffer.wrap(Base64.getDecoder().decode(encrypted.getBytes(UTF_8)));
    DecryptRequest request = new DecryptRequest().withCiphertextBlob(ciphertext);
    ByteBuffer plaintext = awsKmsClient.decrypt(request).getPlaintext();
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(plaintext.array());
    try {
      return KeyFactory.getInstance(ASYMMETRIC_ENCRYPTION_ALGORITHM).generatePrivate(keySpec);
    } catch (GeneralSecurityException e) {
      throw new RuntimeException("Can not decode private key.", e);
    }
  }

  private SecretKey getSymmetricKey(String base64EncodedSymmetricKey) {
    byte[] keyBytes = Base64.getDecoder().decode(base64EncodedSymmetricKey);
    return new SecretKeySpec(keyBytes, SYMMETRIC_ENCRYPTION_ALGORITHM);
  }

  private String decryptWithRsaKey(String base64EncodedCiphertext, PrivateKey privateKey) {
    return decrypt(TRANSFORMATION, base64EncodedCiphertext, privateKey);
  }

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
