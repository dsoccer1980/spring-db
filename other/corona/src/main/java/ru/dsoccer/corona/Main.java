package ru.dsoccer.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Main.class);
    Hospital hospital = context.getBean(Hospital.class);
    hospital.process(new Patient("Шаман"));
  }

}
