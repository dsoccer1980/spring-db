package ru.dsoccer1980.topic.config;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.dsoccer1980.topic.receiver.Tut3Receiver;


@Configuration
public class Config {

  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;
  @Autowired
  private Environment environment;

  @Bean
  public TopicExchange direct() {
    return new TopicExchange(exchangerName);
  }

  @Bean
  public Queue autoDeleteQueue1() {
    return new AnonymousQueue();
  }

  @Bean
  public Declarables binding(TopicExchange topic, Queue autoDeleteQueue1) {
    String[] keys = get();
    Collection<Declarable> list = new ArrayList<>();
    for (String key : keys) {
      System.out.println(key);
      list.add(BindingBuilder.bind(autoDeleteQueue1).to(topic).with(key));
    }
    return new Declarables(list);
  }

  @Bean
  public Tut3Receiver receiver() {
    return new Tut3Receiver();
  }

  private String[] get() {
    if (!environment.containsProperty("rabbitmq.keys")) {
      throw new IllegalArgumentException("Please add argument rabbitmq.keys");
    }
    String keysStr = environment.getProperty("rabbitmq.keys");
    return keysStr.split(",");
  }


}