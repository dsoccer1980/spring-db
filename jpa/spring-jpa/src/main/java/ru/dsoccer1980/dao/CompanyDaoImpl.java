package ru.dsoccer1980.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Company;

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
  public Optional<Company> getById(long id) {
    return Optional.ofNullable(entityManager.find(Company.class, id));
  }

  @Override
  @Transactional
  public void deleteAll() {
    Query query = entityManager.createQuery("DELETE FROM Company");
    query.executeUpdate();

  }
}
