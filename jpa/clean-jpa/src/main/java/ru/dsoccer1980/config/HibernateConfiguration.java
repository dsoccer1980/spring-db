package ru.dsoccer1980.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

public class HibernateConfiguration {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
            .configure("hibernate.cfg.xml");

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

