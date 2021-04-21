package ru.dsoccer.corona.treatment;

import org.springframework.stereotype.Component;
import ru.dsoccer.corona.Patient;

@Component
public class Мед implements Лечение {

  @Override
  public void лечить(Patient patient) {
    System.out.println("лечим медом");
  }
}
