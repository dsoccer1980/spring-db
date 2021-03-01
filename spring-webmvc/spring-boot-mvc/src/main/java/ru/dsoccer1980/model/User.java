package ru.dsoccer1980.model;

public class User {

  private String login;
  private String password;
  private String deviceID;

  public User() {
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDeviceID() {
    return deviceID;
  }

  public void setDeviceID(String deviceID) {
    this.deviceID = deviceID;
  }

  @Override
  public String toString() {
    return "User{" +
        "login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", deviceID='" + deviceID + '\'' +
        '}';
  }
}
