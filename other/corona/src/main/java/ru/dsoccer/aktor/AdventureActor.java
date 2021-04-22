package ru.dsoccer.aktor;


import org.springframework.stereotype.Component;

@Component
@Adventure
public class AdventureActor implements Actor {


  @Override
  public void doAction() {
    System.out.println("Adventure");

  }
}

