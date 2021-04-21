package ru.dsoccer.corona.doctor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dsoccer.corona.Patient;
import ru.dsoccer.corona.treatment.Лечение;

@Component
public class ВсеЛечения implements Целитель {

  @Autowired
  private List<Лечение> лечениеs;

  @Override
  public void исцелять(Patient patient) {
    System.out.println("Я - ВсеЛечения. Лечение: " + getType());
    лечениеs.forEach(лечение -> лечение.лечить(patient));
  }

  @Override
  public String getType() {
    return "Все";
  }
}
