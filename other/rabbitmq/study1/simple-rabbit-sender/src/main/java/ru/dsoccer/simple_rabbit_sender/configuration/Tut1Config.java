package ru.dsoccer.simple_rabbit_sender.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dsoccer.simple_rabbit_sender.sender.Tut1Sender;

@Configuration
public class Tut1Config {

  @Bean
  public Tut1Sender sender() {
    return new Tut1Sender();
  }

}