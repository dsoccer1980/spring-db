package ru.dsoccer.fanout.sender;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut3Sender {

  @Autowired
  private RabbitTemplate template;
  @Value("${rabbitmq.exchanger.name}")
  private String exchangerName;
  private AtomicInteger counter = new AtomicInteger(1);
  private AtomicInteger msgIndex = new AtomicInteger(1);

  @Scheduled(fixedDelay = 1000, initialDelay = 500)
  public void send() {
    String message = getMsgToSend();
    changeCounter();
    template.convertAndSend(exchangerName, "", message);
    System.out.println(" [x] Sent '" + message + "'");
  }

  private String getMsgToSend() {
    return "Msg:" + msgIndex.getAndIncrement() + ". Wait " + counter.getAndIncrement();
  }

  private void changeCounter() {
    if (counter.get() > 5) {
      counter.set(1);
    }
  }

}
