package ru.dsoccer1980;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.JpaConfig;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;
import ru.dsoccer1980.dao.CompanyDao;
import ru.dsoccer1980.domain.Address;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;
import ru.dsoccer1980.domain.Company;


public class Main {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        JpaConfig.class);
    CompanyDao companyDao = context.getBean(CompanyDao.class);
    companyDao.save(new Company("1117", new Address("denis", "2229")));
    companyDao.save(new Company("3337", new Address("max", "4449")));
    companyDao.findAll().forEach(System.out::println);

    AuthorDao authorDao = context.getBean(AuthorDao.class);
    Author author = new Author("Пушкин");
   // authorDao.save(author);


    //  authorDao.insert("Новый");
    authorDao.findAll().forEach(System.out::println);

    System.out.println(authorDao.findIdByName("Пушкин"));
    //System.out.println(authorDao.getById(1));

    BookDao bookDao = context.getBean(BookDao.class);
    // authorDao.insert(new Author(1, "Denis"));


     bookDao.save(new Book("Новый", author));
    bookDao.findAll().forEach(System.out::println);

  }
}
