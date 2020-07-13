package ru.dsoccer1980.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;
import ru.dsoccer1980.dto.CompanyPhoneDto;

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
  public List<CompanyPhoneDto> getCompanyPhone() {
    return entityManager
        .createQuery("Select new ru.dsoccer1980.dto.CompanyPhoneDto(c.name, c.contactPerson.phone) from Company c", CompanyPhoneDto.class)
        .getResultList();
  }

  @Override
  public List<Company> getAllWithGraph() {
    TypedQuery<Company> query = entityManager.createQuery("Select c From Company c ", Company.class)
        .setHint(QueryHints.FETCHGRAPH, entityManager.getEntityGraph(Company.GRAPH_WITH_PERSONS));
    List<Company> resultList = query.getResultList();
    resultList.forEach(c -> c.getPersons().size());
    return resultList;
  }
}
