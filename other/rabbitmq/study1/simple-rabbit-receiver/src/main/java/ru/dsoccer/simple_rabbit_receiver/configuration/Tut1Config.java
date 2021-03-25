package ru.dsoccer.simple_rabbit_receiver.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dsoccer.simple_rabbit_receiver.receiver.Tut1Receiver;


@Configuration
public class Tut1Config {

  @Value("${queue.name}")
  private String queueName;

  @Bean
  public Queue hello() {
    return new Queue(queueName);
  }

  @Bean
  public Tut1Receiver receiver() {
    return new Tut1Receiver();
  }

}