package ru.dsoccer1980.ioc;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("ru.dsoccer1980.ioc")
public class Config {

  @Bean
  public ApplicationListener listener() {
    return new CustomRefreshListener();
  }

  @Bean
  public Quoter quoter() {
    TerminatorQuoter terminatorQuoter = new TerminatorQuoter();
    terminatorQuoter.setMsg("I ll be back");
    return terminatorQuoter;
  }

  @Bean
  public Weapon weapon() {
    return new WeaponImpl();
  }

//  @Bean
//  public SingletonBean singletonBean() {
//    return new SingletonBean();
//  }
//
//  @Bean
//  @Scope(value = SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
//  public PrototypeBean prototypeBean() {
//    return new PrototypeBean();
//  }

}
