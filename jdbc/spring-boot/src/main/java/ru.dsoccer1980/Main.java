package ru.dsoccer1980;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dsoccer1980.dao.BookDaoJdbc;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class);
        BookDaoJdbc bean = run.getBean(BookDaoJdbc.class);
        bean.getAll().forEach(System.out::println);
    }
}
