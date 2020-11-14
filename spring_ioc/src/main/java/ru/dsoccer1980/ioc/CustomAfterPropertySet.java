package ru.dsoccer1980.ioc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class CustomAfterPropertySet  implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("afterPropertiesSet");
  }
}
