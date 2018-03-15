package com.usermind.usermindsdk.worker.writers;

import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.worker.normalization.NormalizedEntity;
import com.usermind.usermindsdk.worker.normalization.metadata.EntityMetadataMerger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base entity writer - defines both API and some re-usable behavior.
 * After all entities have been written, entity writer must be closed to flush all internal
 * buffers.
 */
public abstract class EntityWriter implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityWriter.class);

  private final MetricsReporter<MetricsCollectorClient> metricsReporter;

  protected final EntityMetadataMerger metadataMerger;

  protected EntityWriter(MetricsReporter<MetricsCollectorClient> metricsReporter,
                         EntityMetadataMerger metadataMerger) {
    this.metricsReporter = checkNotNull(metricsReporter);
    this.metadataMerger = checkNotNull(metadataMerger);
  }

  /**
   * Writes a single entity.
   * @param entity entity
   */
  public abstract void writeEntity(NormalizedEntity entity);

  /**
   * Writes a list of entities.
   * @param entities list of entities
   */
  public void writeEntities(List<NormalizedEntity> entities) {
    checkNotNull(entities);

    LOGGER.debug("Writing {} entities", entities.size());
    entities.forEach(this::writeEntity);
  }

  /**
   * Write entity metadata.
   * @param entityType entity type.
   * @param metadata metadata.
   */
  public void writeMetadata(String entityType, Configuration metadata) {
    metadataMerger.merge(entityType, metadata);
  }

  /**
   * Releases system resources and flushes internal buffers (if there are any).
   */
  @Override
  public abstract void close();

  /**
   * Metrics reporter for internal usage.
   * @return metrics reporter
   */
  protected MetricsReporter<MetricsCollectorClient> getMetricsReporter() {
    return metricsReporter;
  }
}
