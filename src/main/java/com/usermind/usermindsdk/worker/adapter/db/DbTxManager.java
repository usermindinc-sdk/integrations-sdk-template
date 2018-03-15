package com.usermind.usermindsdk.worker.adapter.db;

import com.google.common.base.Throwables;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.FailsafeException;
import net.jodah.failsafe.RetryPolicy;
import org.jooq.DSLContext;
import org.jooq.TransactionalCallable;
import org.jooq.TransactionalRunnable;
import org.jooq.impl.DSL;

import java.sql.SQLException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Database Transaction manager.
 */
public class DbTxManager {

  private static int DEFAULT_MAX_RETRIES = 3;
  private static long DEFAULT_INITIAL_DELAY_MS = 50;
  private static long DEFAULT_MAX_DELAY_MS = 300;
  private static long DEFAULT_MAX_DURATION_MS = 5000;
  private static RetryPolicy DEFAULT_RETRY_POLICY = new RetryPolicy()
      .withBackoff(DEFAULT_INITIAL_DELAY_MS, DEFAULT_MAX_DELAY_MS, MILLISECONDS)
      .withMaxDuration(DEFAULT_MAX_DURATION_MS, MILLISECONDS)
      .withMaxRetries(DEFAULT_MAX_RETRIES);

  private final DSLContext context;
  private final RetryPolicy retryPolicy;

  /**
   * Ctor.
   * @param dataSourceHolder data source holder
   */
  public DbTxManager(DataSourceHolder dataSourceHolder) {
    this(dataSourceHolder, DEFAULT_RETRY_POLICY);
  }

  /**
   * Ctor.
   * @param context DSL context
   */
  public DbTxManager(DSLContext context) {
    this(context, DEFAULT_RETRY_POLICY);
  }

  /**
   * Ctor.
   * @param holder data source holder
   * @param retryPolicy retry policy
   */
  public DbTxManager(DataSourceHolder holder, RetryPolicy retryPolicy) {
    this(DSL.using(holder.getDataSource(), holder.getSqlDialect()), retryPolicy);
  }

  /**
   * Ctor.
   * @param context DSL context
   * @param retryPolicy retry policy
   */
  public DbTxManager(DSLContext context, RetryPolicy retryPolicy) {
    this.context = context;
    this.retryPolicy = retryPolicy;
  }

  /**
   * Gets connection to the DB and executes the transactional runnable
   * in the context of a transaction.
   *
   * @param transactional tx runnable.
   * @throws SQLException .
   */
  public void withTransaction(TransactionalRunnable transactional) throws SQLException {
    try {
      Failsafe.with(retryPolicy).run(() -> context.transaction(transactional));
    } catch (FailsafeException e) {
      Throwable cause = e.getCause();
      if (cause instanceof SQLException) {
        throw (SQLException) cause;
      }
      throw Throwables.propagate(cause);
    }
  }

  /**
   * Gets connection to the DB and executes the transactional callable
   * in the context of a transaction.
   *
   * @param <T> Type of return value.
   * @param transactional tx runnable.
   * @return T value of the transaction
   * @throws SQLException .
   */
  public <T> T withTransactionResult(TransactionalCallable<T> transactional) throws SQLException {
    try {
      return Failsafe.with(retryPolicy).get(() -> context.transactionResult(transactional));
    } catch (FailsafeException e) {
      Throwable cause = e.getCause();
      if (cause instanceof SQLException) {
        throw (SQLException) cause;
      }
      throw Throwables.propagate(cause);
    }
  }
}
