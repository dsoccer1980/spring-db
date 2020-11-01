package ru.dsoccer1980.ioc;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

@Profiling
public class TerminatorQuoter implements Quoter {

  @InjectRandomInt(min = 1, max = 5)
  private int repeat;

  private String msg;

  @Value("${my.msg}")
  private String myMsg;

  @PostConstruct
  public void init() {
    System.out.println("init(" + repeat + ")");
    System.out.println(myMsg);
  }

  public TerminatorQuoter() {
    System.out.println("Constructor");
  }

  @Override
  public void sayQoute() {
    for (int i= 0; i < repeat; i++) {
      System.out.println(msg);
    }
  }

  @PostProxy
  @Override
  public void postProxy() {
    System.out.println("RefreshListener (postProxy)");
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}