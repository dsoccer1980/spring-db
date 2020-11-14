package ru.dsoccer1980.ioc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<CustomSpringEvent> {

  @Override
  public void onApplicationEvent(CustomSpringEvent customSpringEvent) {
    System.out.println("Received spring event " + customSpringEvent.getMessage());
  }
}
