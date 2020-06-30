package ru.dsoccer1980;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerShared extends PostgreSQLContainer<PostgreSQLContainerShared> {

  private static final String IMAGE_VERSION = "postgres:11.1";

  private static PostgreSQLContainerShared container;


  private PostgreSQLContainerShared() {
    super(IMAGE_VERSION);
  }

  public static PostgreSQLContainerShared getInstance() {
    if (container == null) {
      container = new PostgreSQLContainerShared();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
    //do nothing, JVM handles shut down
  }
}
