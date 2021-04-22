package ru.dsoccer.aktor;

import org.springframework.stereotype.Component;

@Component
@Thriller
public class ThrillerActor implements Actor {

  @Override
  public void doAction() {
    System.out.println("Thriller");

  }

}
