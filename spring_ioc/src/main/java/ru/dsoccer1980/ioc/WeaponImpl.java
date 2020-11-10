package ru.dsoccer1980.ioc;

public class WeaponImpl implements Weapon {

  @Override
  public void action() {
    System.out.println("Shoot");
  }
}
