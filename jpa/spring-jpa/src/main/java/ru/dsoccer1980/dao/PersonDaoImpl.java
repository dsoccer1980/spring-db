package ru.dsoccer1980.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

@Repository
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
}
