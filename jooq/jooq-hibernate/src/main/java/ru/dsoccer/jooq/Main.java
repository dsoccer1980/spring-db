package ru.dsoccer.jooq;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer.jooq.config.SpringConfiguration;
import ru.dsoccer.jooq.dao.CompanyDao;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    CompanyDao companyDao = context.getBean(CompanyDao.class);
    System.out.println(companyDao.getAll());
  }

}
