package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

@Repository
@SuppressWarnings("JpaQlInspection")
public class PersonDaoImpl implements PersonDao {

  @PersistenceContext
  private Session session;

  @Override
  @Transactional
  public void insert(Person person) {
    session.save(person);
  }

  @Override
  @Transactional
  public void updatePersonWithName(long id, String name) {
    Person person = session.get(Person.class, id);
    session.detach(person);
    person.setName(name);
    Person person2 = session.get(Person.class, id);
    session.merge(person);
    person.setName("nnnnnnnn2");
    System.out.println(person);
    session.saveOrUpdate(person);
  }

  @Override
  @Transactional
  public void addCompanyById(Person person, long id) {
    Company refCompany = session.getReference(Company.class, id);
    person.setCompany(refCompany);
    session.merge(person);
  }

  @Override
  @Transactional
  public void addCompanyByPersonIdAndCompanyId(long personId, long companyId) {
    Person person = session.getReference(Person.class, personId);
    Company refCompany = session.getReference(Company.class, companyId);
    person.setCompany(refCompany);
    session.merge(person);
  }

  @Override
  @Transactional
  public void addCompany(Person person, Company company) {
    Company refCompany = session.getReference(Company.class, company.getId());
    person.setCompany(refCompany);
    session.merge(person);
  }

  @Override
  public Optional<Person> getById(long id) {
    return Optional.ofNullable(session.find(Person.class, id));
  }

  @Override
  @Transactional
  public void deleteAll() {
    Query query = session.createQuery("DELETE FROM Person");
    query.executeUpdate();
  }

  @Override
  @Transactional
  public void deleteById(long id) {
    Person person = session.getReference(Person.class, id);
    session.remove(person);
  }

  @Override
  @Transactional
  public void delete(Person person) {
    session.remove(person);
  }

  @Override
  public Person getReference(long id) {
    return session.getReference(Person.class, id);
  }

  @Override
  public List<Person> getAll() {
    return session.createQuery("Select p from Person p ORDER BY p.company.id NULLS LAST", Person.class).getResultList();
  }

  @Override
  public List<Person> getAllWithNamedQuery() {
    return session.createNamedQuery("all person", Person.class)
        .setParameter("name", "pers1")
        .getResultList();
  }

  @Override
  public List<Person> getPersonsWithCompany() {
    return session.createQuery("Select p from Person p JOIN FETCH p.company", Person.class).getResultList();
  }

  @Override
  public List<Person> getPersonsWithCompanyEntityGraph() {
    EntityGraph<Person> entityGraph = session.createEntityGraph(Person.class);
    entityGraph.addAttributeNodes("company");
    TypedQuery<Person> query = session.createQuery("Select p from Person p", Person.class)
        .setHint(QueryHints.FETCHGRAPH, entityGraph);
    return query.getResultList();
  }

  @Override
  @Transactional
  public void testQuery(Long id1, Long id2) {
    Person person = new Person("person");
    session.persist(person);;
  }
}
