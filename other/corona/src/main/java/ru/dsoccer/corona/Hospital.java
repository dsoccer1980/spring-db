package ru.dsoccer.corona;

import ru.dsoccer.corona.doctor.Целитель;

public interface Hospital {

  void register(String type, Целитель целитель);

  void process(Patient patient);


}
