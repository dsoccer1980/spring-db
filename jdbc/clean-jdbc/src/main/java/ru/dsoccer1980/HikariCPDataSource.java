package ru.dsoccer1980;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPDataSource {

  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;

  static {
    config.setJdbcUrl("jdbc:h2:mem:test");
    config.setUsername("user");
    config.setPassword("password");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    ds = new HikariDataSource(config);
  }

  private HikariCPDataSource() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
