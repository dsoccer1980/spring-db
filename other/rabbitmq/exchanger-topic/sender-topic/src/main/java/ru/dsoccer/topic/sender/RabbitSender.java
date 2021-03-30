package ru.dsoccer.topic.sender;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

public class RabbitSender {

  @Autowired
  private RabbitTemplate template;
  @Autowired
  private Environment environment;

  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;
  private AtomicInteger counter = new AtomicInteger(1);
  private AtomicInteger msgIndex = new AtomicInteger(1);

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    String message = getMsgToSend();
    changeCounter();
    template.convertAndSend(exchangerName, getRoutingKey(), message);
    System.out.println(" [x] Sent '" + message + "'");
  }

  private String getRoutingKey() {
    String[] rabbitKeys = getRabbitKeys();
    return rabbitKeys[msgIndex.get() % rabbitKeys.length];
  }

  private String getMsgToSend() {
    return "Msg:" + msgIndex.getAndIncrement() + ". Key:" + getRoutingKey() + ". Wait " + counter.getAndIncrement();
  }

  private void changeCounter() {
    if (counter.get() > 5) {
      counter.set(1);
    }
  }

  private String[] getRabbitKeys() {
    if (!environment.containsProperty("rabbitmq.keys")) {
      throw new IllegalArgumentException("Please add argument rabbitmq.keys");
    }
    String keysStr = environment.getProperty("rabbitmq.keys");
    return keysStr.split(",");
  }

}
