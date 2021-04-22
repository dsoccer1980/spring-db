package ru.dsoccer.aktor;

import org.springframework.stereotype.Component;

@Component
@Comedy @Thriller
public class ComedyAndThrillerActor implements Actor {


  @Override
  public void doAction() {
    System.out.println("ComedyAndThriller");

  }
}
