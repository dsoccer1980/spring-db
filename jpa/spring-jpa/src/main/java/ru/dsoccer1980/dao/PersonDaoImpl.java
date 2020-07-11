package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

@Repository
//@SuppressWarnings("JpaQlInspection")
public class PersonDaoImpl implements PersonDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void insert(Person person) {
    entityManager.persist(person);
  }

  @Override
  @Transactional
  public void addCompany(Person person, Company company) {
    Company refCompany = entityManager.getReference(Company.class, company.getId());
    person.setCompany(refCompany);
    entityManager.merge(person);
  }

  @Override
  public Optional<Person> getById(long id) {
    return Optional.ofNullable(entityManager.find(Person.class, id));
  }

  @Override
  @Transactional
  public void deleteAll() {
    Query query = entityManager.createQuery("DELETE FROM Person");
    query.executeUpdate();

  }

  @Override
  public List<Person> getAll() {
    return entityManager.createQuery("Select p from Person p", Person.class).getResultList();
  }

  @Override
  @Transactional
  public void testQuery(Long id1, Long id2) {
    Person person = new Person("person");
    entityManager.persist(person);;
  }
}
