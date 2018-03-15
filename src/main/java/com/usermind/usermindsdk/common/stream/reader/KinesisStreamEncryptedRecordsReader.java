package com.usermind.usermindsdk.common.stream.reader;

import com.amazonaws.services.kinesis.model.Record;
import com.amazonaws.services.kms.AWSKMS;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.usermind.integrations.cipher.util.HybridCipher;
import com.usermind.integrations.cipher.util.HybridCipher.DecipherRequest;
import com.usermind.integrations.cipher.util.HybridCipher.DecryptionResult;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * {@code KinesisStreamReader} implementation which decrypts {@code HybridCipher} encrypted stream
 * records.
 */
public class KinesisStreamEncryptedRecordsReader implements KinesisStreamReader<Record> {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private final HybridCipher.Decryptor decryptor;
  private final KinesisStreamReader<Record> rawRecordsReader;

  private KinesisStreamEncryptedRecordsReader(HybridCipher.Decryptor decryptor,
      KinesisStreamReader<Record> rawRecordsReader) {

    this.decryptor = checkNotNull(decryptor);
    this.rawRecordsReader = checkNotNull(rawRecordsReader);
  }

  @Override
  public List<Record> getRecords(int limit) {
    List<Record> records = rawRecordsReader.getRecords(limit);

    try {
      for (Record record : records) {
        ByteBuffer encryptedBytes = record.getData();
        String encryptedJson = new String(encryptedBytes.array(), UTF_8);
        EncryptedData encryptedData = OBJECT_MAPPER.readValue(encryptedJson, EncryptedData.class);

        DecipherRequest decipherRequest = new DecipherRequest()
            .withEncryptedData(encryptedData.getBase64EncodedEncryptedData())
            .withEncryptedSymmetricKey(encryptedData.getBase64EncodedEncryptedSymmetricKey());

        DecryptionResult decryptionResult = decryptor.decrypt(decipherRequest);
        record.setData(ByteBuffer.wrap(decryptionResult.getData()));
      }
    } catch (Exception ex) {
      throw new RuntimeException("Failed to decrypt records from the underlying stream reader", ex);
    }

    return records;
  }

  @Override
  public Map<String, String> getCheckpoints() {
    return rawRecordsReader.getCheckpoints();
  }

  /**
   * POJO used to deserialize an encrypted stream record.
   */
  private static class EncryptedData {

    @JsonProperty("__um__key")
    private String base64EncodedEncryptedSymmetricKey;
    @JsonProperty("__um__data")
    private String base64EncodedEncryptedData;

    /**
     * Base64 encoded private key encrypted symmetric key.
     * @return symmetric key
     */
    public String getBase64EncodedEncryptedSymmetricKey() {
      return base64EncodedEncryptedSymmetricKey;
    }

    /**
     * Base64 encoded symmetric key encrypted data payload.
     * @return data payload
     */
    public String getBase64EncodedEncryptedData() {
      return base64EncodedEncryptedData;
    }
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static class Builder {

    private AWSKMS kmsClient;
    private String base64EncodedEncryptedPrivateKey;
    private KinesisStreamReader<Record> rawRecordsReader;

    public Builder setKmsClient(AWSKMS kmsClient) {
      this.kmsClient = checkNotNull(kmsClient);
      return this;
    }

    /**
     * Base64 encoded KMS encrypted private key.
     * @param base64EncodedEncryptedPrivateKey private key
     * @return this
     */
    public Builder setBase64EncodedEncryptedPrivateKey(
        String base64EncodedEncryptedPrivateKey) {

      this.base64EncodedEncryptedPrivateKey = checkNotNull(base64EncodedEncryptedPrivateKey);
      return this;
    }

    /**
     * An underlying raw records reader.
     * @param rawRecordsReader raw records reader
     * @return this
     */
    public Builder setRawRecordsReader(KinesisStreamReader<Record> rawRecordsReader) {
      this.rawRecordsReader = checkNotNull(rawRecordsReader);
      return this;
    }

    /**
     * Builds a {@code KinesisStreamEncryptedRecordsReader}.
     * @return new {@code KinesisStreamEncryptedRecordsReader}
     */
    public KinesisStreamEncryptedRecordsReader build() {
      checkState(kmsClient != null);
      checkState(!Strings.isNullOrEmpty(base64EncodedEncryptedPrivateKey));

      HybridCipher.Decryptor decryptor =
          HybridCipher.getDecryptor(kmsClient, base64EncodedEncryptedPrivateKey);

      return new KinesisStreamEncryptedRecordsReader(decryptor, rawRecordsReader);
    }
  }
}
