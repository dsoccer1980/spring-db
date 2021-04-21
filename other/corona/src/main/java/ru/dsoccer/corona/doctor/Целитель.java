package ru.dsoccer.corona.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dsoccer.corona.Hospital;
import ru.dsoccer.corona.Patient;

public interface Целитель {

  void исцелять(Patient patient);

  String getType();

  @Autowired
  default void regMe(Hospital hospital) {
    hospital.register(getType(), this);
  }

}
