package ru.dsoccer.aktor;

import org.springframework.stereotype.Component;

@Component
@AnyGenre
public class AnyGenreActor implements Actor {

  @Override
  public void doAction() {
    System.out.println("any genre");
  }
}
