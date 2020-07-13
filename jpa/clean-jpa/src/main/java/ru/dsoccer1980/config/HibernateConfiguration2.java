package ru.dsoccer1980.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

public class HibernateConfiguration2 {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb")
            .setProperty("hibernate.connection.username", "root")
            .setProperty("hibernate.connection.password", "root")
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.hbm2ddl.auto", "create");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
            .addAnnotatedClass(Author.class)
            .addAnnotatedClass(Book.class)
            .getMetadataBuilder()
            .build();

        return metadata.getSessionFactoryBuilder().build();
    }

}

