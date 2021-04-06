package ru.dsoccer.jooq.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer.jooq.domain.Company;
import ru.dsoccer.jooq.domain.Person;


@Repository
public class CompanyDaoImpl implements CompanyDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public void insert(Company company) {
    entityManager.persist(company);

  }

  @Override
  @Transactional
  public void insertWithPerson(Company company, Person person) {
    // EntityTransaction transaction = entityManager.getTransaction();
    // transaction.begin();
    // entityManager.persist(company);
    company.setPersons(Collections.singletonList(person));
    entityManager.persist(company);
    // transaction.commit();
  }

  @Override
  public Optional<Company> getById(long id) {
    return Optional.ofNullable(entityManager.find(Company.class, id));
  }

  @Override
  @Transactional
  public void deleteAll() {
    Query query = entityManager.createQuery("DELETE FROM Company");
    query.executeUpdate();
  }

  @Override
  public Optional<Company> getCompanyWithPerson(Company company) {
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c LEFT JOIN FETCH c.persons Where c.id=:id", Company.class);
    query.setParameter("id", company.getId());
    return Optional.ofNullable(query.getSingleResult());
  }

  @Override
  public List<Company> getAll() {
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c ", Company.class);
    return query.getResultList();
  }

  @Override
  public List<Company> getAllWithNamedGraph() {
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c ", Company.class)
        .setHint(QueryHints.FETCHGRAPH, entityManager.getEntityGraph(Company.GRAPH_WITH_PERSONS));
    List<Company> resultList = query.getResultList();
    resultList.forEach(c -> c.getPersons().size());
    return resultList;
  }

  @Override
  public List<Company> getAllWithGraph() {
    EntityGraph<Company> graph = entityManager.createEntityGraph(Company.class);
    graph.addAttributeNodes("persons");
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c ", Company.class)
        .setHint(QueryHints.FETCHGRAPH, graph);
    List<Company> resultList = query.getResultList();
    resultList.forEach(c -> c.getPersons().size());
    return resultList;
  }

  @Override
  @Transactional
  public List<Company> getCompaniesWithPersonMax(int count) {
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c JOIN FETCH c.persons", Company.class);
    query.setMaxResults(count);
    return query.getResultList();
  }



}
