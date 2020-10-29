package ru.dsoccer1980;

import java.util.Optional;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.SpringConfiguration;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.CompanyDao;
import ru.dsoccer1980.dao.PersonDao;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.ContactPerson;
import ru.dsoccer1980.domain.Person;

public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SpringConfiguration.class);
    AuthorDao authorDao = applicationContext.getBean(AuthorDao.class);
    BookDao bookDao = applicationContext.getBean(BookDao.class);
    PersonDao personDao = applicationContext.getBean(PersonDao.class);
    CompanyDao companyDao = applicationContext.getBean(CompanyDao.class);

    //companyDao.insertWithPerson(new Company("comp2", new ContactPerson("c2", "l2", "p2")), new Person("pers2"));

//    Author sasha = new Author("Sasha2");
//    authorDao.insert("mama2");
//    System.out.println(sasha);
//
//    Author author = authorDao.getById(4).orElse(null);
//    author.setName("Denis2");
//    authorDao.insert(author);
//
//   authorDao.getAll().forEach(System.out::println);
   // bookDao.getAll().forEach(System.out::println);

  //  authorDao.getCriteria();

    personDao.getAll().forEach(System.out::println);
//    Person person = new Person("person2");
//    personDao.insert(person);


//    personDao.addCompanyByPersonIdAndCompanyId(14, 9);
//
//   personDao.getPersonsWithCompany().forEach(p -> System.out.println(p + " " +  p.getCompany()));

  }
}
