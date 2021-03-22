package ru.dsoccer;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.hibernate.graph.GraphSemantic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dsoccer.model.Car;
import ru.dsoccer.repository.CarRepository;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class);

    CarRepository bean = context.getBean(CarRepository.class);
    EntityManagerFactory managerFactory = context.getBean(EntityManagerFactory.class);
    EntityManager em = managerFactory.createEntityManager();
    EntityGraph<Car> entityGraph = em.createEntityGraph(Car.class);
    entityGraph.addAttributeNodes("details");
    TypedQuery<Car> query = em.createQuery("Select c from Car c ", Car.class);
    query.setHint(GraphSemantic.FETCH.getJpaHintName(), entityGraph);
    System.out.println(query.getResultList());

  }

}
