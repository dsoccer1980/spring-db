package ru.dsoccer.simple_rabbit_sender.sender;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

  @Autowired
  private RabbitTemplate template;

  @Value("${queue.name}")
  private String queueName;

  private AtomicInteger counter = new AtomicInteger();

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    String message = "Hello World! " + counter.getAndIncrement();
    this.template.convertAndSend(queueName, message);
    System.out.println(" [x] Sent '" + message + "'");
  }
}
