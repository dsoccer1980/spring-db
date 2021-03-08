package ru.dsoccer1980.ioc;

import org.springframework.beans.factory.annotation.Autowired;

public class SingletonBean {

  @Autowired
  private PrototypeBean prototypeBean;

  public SingletonBean() {
    System.out.println("----------SingletonBean created");
  }


  public PrototypeBean getPrototypeBean() {
   System.out.println("-------" + prototypeBean.toString());
    return null;
  }
}
