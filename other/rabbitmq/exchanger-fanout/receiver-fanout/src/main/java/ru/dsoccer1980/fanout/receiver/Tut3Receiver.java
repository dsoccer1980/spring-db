package ru.dsoccer1980.fanout.receiver;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

public class Tut3Receiver {

  private Logger logger = LoggerFactory.getLogger(Tut3Receiver.class);

  @Autowired
  private Queue autoDeleteQueue;

  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receive1(String in) throws InterruptedException {
    receive(in, autoDeleteQueue.getName());
  }

  private void receive(String in, String receiver) {
    StopWatch watch = new StopWatch();
    watch.start();
    doWork(getSecondsFromMessage(in));
    watch.stop();
    System.out.println("instance " + receiver + " [x] Received '" + in + "'. Work time " + watch.getTotalTimeSeconds() + "s");
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
