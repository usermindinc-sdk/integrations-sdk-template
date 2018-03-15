package com.usermind.usermindsdk.worker.adapter.db;

import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.fetch.FullFetch;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.jooq.SQLDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by flore on 8/18/16.
 */
public class DataSourceHolder {

  private static final Logger LOGGER = LoggerFactory.getLogger(FullFetch.class);

  private static final String DB_URL_PATH =
      Configuration.buildFullPath("db", "url");
  private static final String DB_USERNAME_PATH =
      Configuration.buildFullPath("db", "username");
  private static final String DB_PASSWORD_PATH =
      Configuration.buildFullPath("db", "password");
  private static final String DB_SQL_DIALECT_PATH =
      Configuration.buildFullPath("db", "sqlDialect");
  private static final String DB_DRIVER_CLASS_NAME_PATH =
      Configuration.buildFullPath("db", "driverClassName");
  private static final String DB_MAX_CONNECTION_PATH =
      Configuration.buildFullPath("db", "maxConnectionCount");
  private static final String DEFAULT_DRIVER_CLASS_NAME =
      "org.postgresql.Driver";
  private static final int DEFAULT_MAX_CONNECTION_COUNT = 2;

  private DataSource dataSource;
  private SQLDialect sqlDialect; //TODO : not sure if this belongs here?

  /**
   * DataSource holder : connection pool.
   * @param dbConfig dbConfig
   * @param applicationName applicationName to be used
   */
  public DataSourceHolder(Configuration dbConfig, String applicationName) {
    dbConfig.checkPathExists(DB_URL_PATH);
    dbConfig.checkPathExists(DB_USERNAME_PATH);
    dbConfig.checkPathExists(DB_PASSWORD_PATH);
    this.sqlDialect = SQLDialect.valueOf(
      dbConfig.getString(DB_SQL_DIALECT_PATH, SQLDialect.POSTGRES.name()));


    String url = dbConfig.getString(DB_URL_PATH);
    final String user = dbConfig.getString(DB_USERNAME_PATH);
    final String pass = dbConfig.getString(DB_PASSWORD_PATH);
    final int maxConnection = dbConfig.getInt(DB_MAX_CONNECTION_PATH, DEFAULT_MAX_CONNECTION_COUNT);

    final String driverClassName =  dbConfig.getString(DB_DRIVER_CLASS_NAME_PATH,
        DEFAULT_DRIVER_CLASS_NAME);
    Properties properties = new Properties();
    properties.setProperty("ApplicationName", applicationName);
    properties.setProperty("connectTimeout", "60"); // seconds

    try {

      LOGGER.info("Creating pooled data source");

      final PoolProperties poolConfig = new PoolProperties();
      poolConfig.setUrl(url);
      poolConfig.setUsername(user);
      poolConfig.setPassword(pass);
      poolConfig.setInitialSize(maxConnection);
      poolConfig.setMaxActive(maxConnection);
      poolConfig.setMaxIdle(maxConnection);
      poolConfig.setDriverClassName(driverClassName);

      // TODO: add config for these properties. Defaults taken from Dropwizard DB
      poolConfig.setAbandonWhenPercentageFull(0);
      poolConfig.setAlternateUsernameAllowed(false);
      poolConfig.setCommitOnReturn(false);
      poolConfig.setRollbackOnReturn(false);
      poolConfig.setDbProperties(properties);
      poolConfig.setDefaultAutoCommit(null);
      poolConfig.setDefaultCatalog(null);
      poolConfig.setDefaultReadOnly(null);
      poolConfig.setDefaultTransactionIsolation(DataSourceFactory.UNKNOWN_TRANSACTIONISOLATION);
      poolConfig.setFairQueue(true);
      poolConfig.setInitialSize(1);
      poolConfig.setInitSQL(null);
      poolConfig.setLogAbandoned(false);
      poolConfig.setLogValidationErrors(false);
      poolConfig.setMinIdle(1);
      poolConfig.setMaxWait((int) TimeUnit.SECONDS.toMillis(90));
      poolConfig.setMinEvictableIdleTimeMillis((int) TimeUnit.SECONDS.toMillis(60L));
      poolConfig.setName("lc39");
      poolConfig.setRemoveAbandoned(false);
      poolConfig.setRemoveAbandonedTimeout((int) TimeUnit.MINUTES.toSeconds(60L));
      poolConfig.setTestWhileIdle(true);
      poolConfig.setValidationQuery("/* Health Check */ SELECT 1");
      poolConfig.setTestOnBorrow(false);
      poolConfig.setTestOnConnect(false);
      poolConfig.setTimeBetweenEvictionRunsMillis((int) TimeUnit.SECONDS.toMillis(5L));
      poolConfig.setValidationInterval((int) TimeUnit.SECONDS.toMillis(30L));

      this.dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolConfig);
    } catch (Exception e) {
      LOGGER.error("Exception when trying to get a database pool", e);
      CommonLib.get().getMetrics().incrementCounter("integrations.lc39.failure",
          "type:DBPoolConnectionInitError");
      throw new RuntimeException(e);
    }
  }

  public javax.sql.DataSource getDataSource() {
    return dataSource;
  }

  public SQLDialect getSqlDialect() {
    return sqlDialect;
  }
}
