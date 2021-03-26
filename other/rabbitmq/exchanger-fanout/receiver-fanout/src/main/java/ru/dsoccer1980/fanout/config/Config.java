package ru.dsoccer1980.fanout.config;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dsoccer1980.fanout.receiver.Tut3Receiver;


@Configuration
public class Config {

  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;

  @Bean
  public FanoutExchange fanout() {
    return new FanoutExchange(exchangerName);
  }

  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }


  @Bean
  public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
    return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
  }


  @Bean
  public Tut3Receiver receiver() {
    return new Tut3Receiver();
  }


}