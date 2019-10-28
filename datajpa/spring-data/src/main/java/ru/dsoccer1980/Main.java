package ru.dsoccer1980;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.JpaConfig;
import ru.dsoccer1980.dao.CompanyDao;
import ru.dsoccer1980.domain.Address;
import ru.dsoccer1980.domain.Company;


public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        JpaConfig.class);
    CompanyDao companyDao = context.getBean(CompanyDao.class);
    companyDao.save(new Company("1117", new Address("denis", "2229")));
    companyDao.save(new Company("3337", new Address("max", "4449")));
    companyDao.findAll().forEach(System.out::println);

  }
}
