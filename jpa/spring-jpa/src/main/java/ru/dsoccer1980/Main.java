package ru.dsoccer1980;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.SpringConfiguration;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            SpringConfiguration.class);
        AuthorDao authorDao = applicationContext.getBean(AuthorDao.class);
        BookDao bookDao = applicationContext.getBean(BookDao.class);

        authorDao.getAll().forEach(System.out::println);
        bookDao.getAll().forEach(System.out::println);
    }
}
