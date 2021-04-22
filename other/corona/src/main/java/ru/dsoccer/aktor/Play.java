package ru.dsoccer.aktor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Play {

  @Autowired
  @Comedy
  @Thriller
  List<Actor> actors;

  public void show() {
    actors.forEach(Actor::doAction);
  }
}
