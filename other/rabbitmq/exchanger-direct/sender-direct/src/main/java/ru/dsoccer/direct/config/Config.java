package ru.dsoccer.direct.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dsoccer.direct.sender.Tut3Sender;

@Configuration
public class Config {

  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;

  @Bean
  public Tut3Sender sender() {
    return new Tut3Sender();
  }

}