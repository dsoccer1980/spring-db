package ru.dsoccer1980;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class);

        AuthorDao authorDao = applicationContext.getBean(AuthorDao.class);
        BookDao bookDao = applicationContext.getBean(BookDao.class);

        authorDao.getAll().forEach(System.out::println);
        bookDao.getAll().forEach(System.out::println);
    }
}
