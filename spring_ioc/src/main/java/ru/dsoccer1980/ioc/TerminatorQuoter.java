package ru.dsoccer1980.ioc;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

@Profiling
public class TerminatorQuoter implements Quoter {

  @Autowired
  private Weapon weapon;

  @Autowired
  private Environment environment;

  @InjectRandomInt(min = 1, max = 5)
  private int repeat;

  private String msg;

  @Value("${my.msg}")
  private String myMsg;

  @PostConstruct
  public void init() {
    System.out.println("init(" + repeat + ")");
    System.out.println("@Value in postconstruct " +  myMsg);
    System.out.println("Autowired in postconstruct  " + weapon);
    System.out.println("env my.msg in postconstruct " + environment.getProperty("my.msg"));
  }

  public TerminatorQuoter() {
    System.out.println("Constructor");
    System.out.println("@Value in constructor " +  myMsg);
    System.out.println("Autowired in constructor " + weapon);
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
