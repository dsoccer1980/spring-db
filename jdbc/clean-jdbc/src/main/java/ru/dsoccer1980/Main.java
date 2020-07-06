package ru.dsoccer1980;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

  private final String URL = "jdbc:h2:mem:";
  private final Connection connection;

  public Main() throws SQLException {
    connection = getConnection();
  }

  public static void main(String[] args) throws SQLException {
    Main main = new Main();
    main.createTable();
    main.insertRecord();
    main.selectRecord();
    main.close();

  }

  private void createTable() {
    try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE users (id int PRIMARY KEY, name VARCHAR(255))")) {
      int count = preparedStatement.executeUpdate();
      connection.commit();
      System.out.println(count);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void insertRecord() {
    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users values(1, 'Denis')")) {
      int count = preparedStatement.executeUpdate();
      connection.commit();
      System.out.println(count);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void selectRecord() {
    try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM users")) {
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          int id = resultSet.getInt(1);
          String name = resultSet.getString(2);
          System.out.println(id + ":" + name);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  private Connection getConnection() throws SQLException {
//    Connection connection = DriverManager.getConnection(URL);
    Connection connection = HikariCPDataSource.getConnection();
    connection.setAutoCommit(false);
    return connection;
  }

  private void close() throws SQLException {
    this.connection.close();
  }


}
