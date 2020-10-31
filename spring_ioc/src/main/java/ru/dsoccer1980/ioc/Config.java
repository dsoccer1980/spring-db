package ru.dsoccer1980.ioc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.dsoccer1980.ioc")
public class Config {

  @Bean
  public ApplicationListener listener() {
    return new CustomRefreshListener();
  }

//  @Bean
//  public Quoter quoter() {
//    TerminatorQuoter terminatorQuoter = new TerminatorQuoter();
//    terminatorQuoter.setMsg("I ll be back");
//    return terminatorQuoter;
//  }

}
