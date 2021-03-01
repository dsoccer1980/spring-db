package ru.dsoccer1980.model;

public class Result {

  private boolean res;
  private String accessToken;
  private String errors;

  public Result() {
  }

  public Result(boolean res, String accessToken, String errors) {
    this.res = res;
    this.accessToken = accessToken;
    this.errors = errors;
  }

  public boolean isRes() {
    return res;
  }

  public void setRes(boolean res) {
    this.res = res;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getErrors() {
    return errors;
  }

  public void setErrors(String errors) {
    this.errors = errors;
  }
}
