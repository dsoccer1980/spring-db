package ru.dsoccer1980;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.SpringConfiguration;
import ru.dsoccer1980.dao.PersonDao;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SpringConfiguration.class);

    PersonDao personDao = applicationContext.getBean(PersonDao.class);
    personDao.getAll().forEach(System.out::println);


  }
}
