package ru.dsoccer.simple_rabbit_receiver.receiver;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = {"hello"})
public class Tut1Receiver {

  private Logger logger = LoggerFactory.getLogger(Tut1Receiver.class);

  @RabbitHandler
  public void receive(String in) {
    int seconds = getSecondsFromMessage(in);
    StopWatch watch = new StopWatch();
    watch.start();
    doWork(seconds);
    watch.stop();
    System.out.println(" [x] Received '" + in + "'. Work time " + 1 + "s");
  }

  private void doWork(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private int getSecondsFromMessage(String message) {
    try {
      return Integer.parseInt(message.substring(message.lastIndexOf(" ") + 1));
    } catch (NumberFormatException e) {
      logger.error("Fail to parse", message);
      return 0;
    }
  }
}
