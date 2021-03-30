package ru.dsoccer.topic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dsoccer.topic.sender.RabbitSender;

@Configuration
public class Config {

  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;

  @Bean
  public RabbitSender sender() {
    return new RabbitSender();
  }

}