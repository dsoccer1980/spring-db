package ru.dsoccer1980;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dsoccer1980.config.SpringJdbcConfiguration;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.BookDao;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringJdbcConfiguration.class);
        AuthorDao authorDao = applicationContext.getBean(AuthorDao.class);
        // authorDao.insert(new Author(1, "Denis"));


        //  authorDao.insert("Новый");
        authorDao.getAll().forEach(System.out::println);

        System.out.println(authorDao.getIdByName("Пушкин"));
        //System.out.println(authorDao.getById(1));

        BookDao bookDao = applicationContext.getBean(BookDao.class);
        // authorDao.insert(new Author(1, "Denis"));


        //  bookDao.insert(new Book("Новый", authorDao.getById(1)));
        bookDao.getAll().forEach(System.out::println);

        //  System.out.println(bookDao.getIdByName("Пушкин"));

    }
}
