package ru.dsoccer1980.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.dsoccer1980.config.HibernateConfiguration;
import ru.dsoccer1980.config.HibernateConfiguration2;
import ru.dsoccer1980.domain.Author;

public class Queries {

  private SessionFactory sessionFactory = HibernateConfiguration2.getSessionFactory();

  public void entityExample() {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();

      Author author = new Author("Denis");
      session.save(author);
      System.out.println(">>>>>>>>>>> created:" + author);

      System.out.println(">>>>>>>>>>> before commit");
      session.getTransaction().commit(); // В этот момент делается insert

      //  session.detach(person); //Вариант с давно существующим объектом

      // А тут select не выполняется, Person берется из кэша L1
      Author selected = session.get(Author.class, author.getId());
      System.out.println(">>>>>>>>>>> selected:" + selected);
    }
  }

}
