package ru.dsoccer1980.ioc.Lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class Car {

  @Lookup
  public Passenger getPassenger() {
    return null;
  }

  public void drive(String name) {
    Passenger passenger = getPassenger();
    passenger.setName(name);
    System.out.println("--------------Car with passenger:" + name + " " + passenger.toString());
  }

}
