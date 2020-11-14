package ru.dsoccer1980.ioc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomSpringEventPublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public void publish(String message) {
    System.out.println("Publish custom event.");
    CustomSpringEvent springEvent = new CustomSpringEvent("this", message);
    applicationEventPublisher.publishEvent(springEvent);


  }

}
