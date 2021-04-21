package ru.dsoccer.corona.doctor;

import org.springframework.stereotype.Component;
import ru.dsoccer.corona.Patient;

@Component
public class DefaultЦелитель implements Целитель {

  @Override
  public void исцелять(Patient patient) {
    System.out.println("Я - DefaultЦелитель");
    System.out.println("Само пройдет");
  }

  @Override
  public String getType() {
    return "DefaultЦелитель";
  }
}
