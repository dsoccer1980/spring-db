package ru.dsoccer.corona.doctor;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.dsoccer.corona.Patient;
import ru.dsoccer.corona.configuration.InjectList;
import ru.dsoccer.corona.treatment.Лечение;
import ru.dsoccer.corona.treatment.Мед;

@Component
public class Знахарь implements Целитель {

  @InjectList({Мед.class})
  private List<Лечение> лечениеs;

  @Override
  public void исцелять(Patient patient) {
    System.out.println("Я - знахарь. Лечение: " + getType());
    лечениеs.forEach(лечение -> лечение.лечить(patient));
  }

  @Override
  public String getType() {
    return "Знахарь";
  }


}
