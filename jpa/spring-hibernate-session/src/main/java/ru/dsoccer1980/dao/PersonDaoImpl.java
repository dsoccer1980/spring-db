package ru.dsoccer1980.dao;

import com.jcabi.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;
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
    Logger.debug(this, "Show all persons");
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
    session.persist(person);
  }

  @Override
  public String doNativeQuery(long id) {
    NativeQuery nativeQuery = session.createNativeQuery("SELECT person_name FROM Person Where id=?");
    nativeQuery.setParameter(1, id);
    return (String) nativeQuery.getSingleResult();
  }

  @Override
  @Transactional
  public void doWorkNativeSql() {
    session.doWork(new Work() {
      @Override
      public void execute(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person(person_name) VALUES('FFFFF')");
        preparedStatement.execute();
      }
    });
  }

  @Override
  public String doReturnWorkNativeSql(long id) {
    return session.doReturningWork(new ReturningWork<String>() {
      @Override
      public String execute(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT person_name FROM Person Where id=?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          return resultSet.getString(1);
        }
        return "no";
      }
    });
  }

  @Override
  public List<Company> getCompaniesWithPersons() {
    TypedQuery<Company> query = session.createQuery("Select DISTINCT  c from Company c JOIN FETCH c.persons", Company.class);
    query.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);  //remove DISTINCT  from SQL query
    List<Company> list = query.getResultList();
    list.forEach(company -> System.out.println(company + " " + company.getPersons()));
    return list;
  }

  @Override
  @Transactional
  public void bulkInsert(List<Person> list) {
//    session.setJdbcBatchSize(5);
    list.forEach(person -> session.persist(person));
  }


}
