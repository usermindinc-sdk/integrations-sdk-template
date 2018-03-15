package com.usermind.usermindsdk.worker.writers.s3;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.google.common.io.Closeables;
import com.google.common.io.CountingOutputStream;
import com.usermind.integrations.cipher.util.HybridCipher;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.writers.exception.EntityWriterException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.zip.GZIPOutputStream;

import static com.google.common.base.Preconditions.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Represents a local chunk of records stored on a file system using the temp file.
 */
public class LocalChunk implements Closeable {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocalChunk.class);
  private static final JsonFactory JSON_FACTORY = new MappingJsonFactory();
  private static final String RAW_RECORDS_SEPARATOR = "\n";
  private static final int BUFFER_SIZE = 1024 * 32;

  private final String runId;
  private final String entityName;
  private final ChunkType chunkType;
  private final Path tempFile;
  private final JsonGenerator jsonGenerator;
  private HybridCipher.CipherPair cipherPair;
  private CountingOutputStream countingOutputStream;

  private int sequenceNumber;
  private int recordsCount;
  private long bytesCount;
  private boolean closed;

  /**
   * {@code LocalChunk} ctor.
   * @param entityName entity name
   * @param runId run id
   * @param sequenceNumber sequence number of this chunk
   * @param chunkType chunk type
   * @param compress whether to compress the data using a GZIP
   * @param encrypt if true this local chunk will be encrypted as it is stored on disk
   */
  public LocalChunk(String entityName, String runId, int sequenceNumber, ChunkType chunkType,
      boolean compress, boolean encrypt) {

    checkArgument(StringUtils.isNotBlank(entityName));
    this.entityName = entityName;
    checkArgument(StringUtils.isNotBlank(runId));
    this.runId = runId;
    this.chunkType = checkNotNull(chunkType);
    this.sequenceNumber = sequenceNumber;

    String suffix = compress ? ".tmp.gz" : ".tmp";
    String prefix = String.join("_", "s3writer", entityName, String.valueOf(sequenceNumber),
        chunkType.name().toLowerCase(), runId);

    Path tempFile = null; // This is to have a ref for deletion in the catch below
    try {
      // We better not use {@code toString} here
      LOGGER.debug("Creating the new local chunk for run id '{}' of entity '{}', type '{}' and "
          + "sequence number '{}'", runId, entityName, chunkType, sequenceNumber);

      this.tempFile = tempFile = Files.createTempFile(prefix, suffix);
      LOGGER.debug("Created a local chunk temp file '{}'", tempFile);

      this.jsonGenerator = createJsonGenerator(tempFile, chunkType, compress, encrypt);
    } catch (IOException | GeneralSecurityException exception) {
      // This is to delete the file in case of a failure on writer creation
      if (tempFile != null) {
        try {
          Files.deleteIfExists(tempFile);
        } catch (IOException exceptionOnDelete) {
          LOGGER.warn("Failed to delete a temp file: " + tempFile, exceptionOnDelete);
        }
      }

      String errorMessage = String.format("Failed to create the local chunk for run id '%s' of "
          + "entity '%s', type '%s' and sequence number '%d'", runId, entityName, chunkType,
          sequenceNumber);
      throw new EntityWriterException(errorMessage, exception);
    }

    this.closed = false;
  }

  // Gets overridden in *unit tests*.
  JsonGenerator createJsonGenerator(Path outputFile, ChunkType chunkType, boolean compress,
                                    boolean encrypt) throws IOException, GeneralSecurityException {

    OutputStream outputStream = Files.newOutputStream(outputFile);

    try {
      if (encrypt) {
        cipherPair = HybridCipher.createSymmetricCipherPair();
        outputStream = new CipherOutputStream(outputStream, cipherPair.getEncryptCipher());
      }
      outputStream = countingOutputStream = new CountingOutputStream(outputStream);
      outputStream = compress ? new GZIPOutputStream(outputStream, BUFFER_SIZE) :
          new BufferedOutputStream(outputStream, BUFFER_SIZE);

      JsonGenerator generator = JSON_FACTORY.createGenerator(outputStream);
      if (chunkType == ChunkType.RAW) {
        generator.setRootValueSeparator(new SerializedString(RAW_RECORDS_SEPARATOR));
      }

      return generator;
    } catch (Exception e) {
      Closeables.close(outputStream, true);
      throw e;
    }
  }

  /**
   * Adds a record to this local chunk.
   * @param record record
   * @throws IllegalStateException if this chunk is already closed
   */
  public void add(Configuration record) {
    checkNotNull(record);
    checkState(!closed, "Attempting to add record to the closed local chunk: %s", this);

    try {
      // If first ever record
      if (recordsCount == 0 && chunkType != ChunkType.RAW) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("records");
      }

      jsonGenerator.writeObject(record);

      recordsCount++;
      bytesCount += record.toString().getBytes(UTF_8).length;
    } catch (IOException ex) {
      throw new EntityWriterException("Failed to add the record to a local chunk: " + toString(),
          ex);
    }
  }

  int getRecordsCount() {
    return recordsCount;
  }

  public long getBytesCount() {
    return bytesCount;
  }

  /**
   * Returns the size of unencrypted temp file in bytes.
   *
   * @return size of unencrypted temp file in bytes.
   * @throws IllegalStateException if the chunk is not closed
   */
  public long getUnencryptedFileSize() {
    checkState(closed, "Cannot get file size as local chunk is still open");

    return countingOutputStream.getCount();
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public String getEntityName() {
    return entityName;
  }

  public ChunkType getChunkType() {
    return chunkType;
  }

  /**
   * Returns a temp file that this chunk is written to.
   *
   * @return path to temp file
   * @throws IllegalStateException if this local chunk is not closed
   */
  public Path getFile() {
    checkState(closed, "File is not ready, local chunk must be closed first");

    return tempFile;
  }

  /**
   * Returns an input stream from a temp file that this chunk is written to. If the file is
   * encrypted, the returned stream will automatically decrypt it.
   *
   * @return input stream from a temp file
   * @throws IllegalStateException if this local chunk is not closed
   * @throws EntityWriterException if the temp file cannot be found
   */
  public InputStream getInputStream() {
    checkState(closed, "Attempting to get an input stream from an open local chunk: %s", this);

    try {
      InputStream inputStream = Files.newInputStream(tempFile);
      if (cipherPair != null) {
        inputStream = new CipherInputStream(inputStream, cipherPair.getDecryptCipher());
      }
      return inputStream;
    } catch (IOException e) {
      throw new EntityWriterException("Could not create an input stream for a local chunk "
          + "file: " + tempFile, e);
    }
  }

  @Override
  public void close() {
    if (closed) {
      LOGGER.warn("Local chunk is already closed: {}", this);
      return;
    }

    LOGGER.debug("Closing the local chunk: {}", this);

    closed = true;

    try (Closeable closeable = jsonGenerator) {
      if (chunkType == ChunkType.RAW) {
        // We write separator at the end - in the way it's done in the Legacy Adapter
        jsonGenerator.writeRaw(RAW_RECORDS_SEPARATOR);
      } else {
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
      }
    } catch (IOException ex) {
      throw new EntityWriterException("Failed to close the local chunk: " + toString(), ex);
    }
  }

  /**
   * Deletes this local chunk by removing the corresponding temp file.
   * @throws IllegalStateException if this chunk is not closed
   */
  public void delete() {
    checkState(closed, "Attempting to delete an open local chunk: %s", this);

    try {
      LOGGER.debug("Deleting the local chunk: {}", this);
      boolean deleted = Files.deleteIfExists(tempFile);
      LOGGER.debug("Local chunk temp file deleted: {}", deleted);
    } catch (IOException ex) {
      LOGGER.warn("Failed to delete the local chunk: " + toString(), ex);
    }
  }

  @Override
  public String toString() {
    return "LocalChunk{"
        + "runId='" + runId + '\''
        + ", entityName='" + entityName + '\''
        + ", chunkType=" + chunkType
        + ", sequenceNumber=" + sequenceNumber
        + ", recordsCount=" + recordsCount
        + ", bytesCount=" + bytesCount
        + '}';
  }
}
