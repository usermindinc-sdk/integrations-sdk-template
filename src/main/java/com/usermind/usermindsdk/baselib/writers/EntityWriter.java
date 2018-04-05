package com.usermind.usermindsdk.baselib.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * Base entity writer - defines both API and some re-usable behavior.
 * After all entities have been written, entity writer must be closed to flush all internal
 * buffers.
 */
public abstract class EntityWriter implements Closeable {

  private static final Logger LOGGER = LoggerFactory.getLogger(EntityWriter.class);


//  protected final EntityMetadataMerger metadataMerger;

  protected EntityWriter() {
    //    this.metadataMerger = checkNotNull(metadataMerger);
  }

  public abstract void writeFile(String fileName, String fileContents);

  //
//  /**
//   * Writes a single entity.
//   * @param entity entity
//   */
  public abstract void writeEntity(String entity);

  /**
   * Writes a list of entities.
   * @param entities list of entities
   */
//  public void writeEntities(List<NormalizedEntity> entities) {
//    checkNotNull(entities);
//
//    LOGGER.debug("Writing {} entities", entities.size());
//    entities.forEach(this::writeEntity);
//  }

  /**
   * Write entity metadata.
   * @param entityType entity type.
   * @param metadata metadata.
   */
//  public void writeMetadata(String entityType, Configuration metadata) {
//    metadataMerger.merge(entityType, metadata);
//  }

  /**
   * Releases system resources and flushes internal buffers (if there are any).
   */
  @Override
  public abstract void close();


}
