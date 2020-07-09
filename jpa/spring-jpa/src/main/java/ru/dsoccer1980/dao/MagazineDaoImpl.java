package ru.dsoccer1980.dao;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Magazine;

@Repository
public class MagazineDaoImpl implements MagazineDao {

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public void insert(Magazine magazine) {
    em.persist(magazine);
  }

  @Override
  public Optional<Magazine> getById(long id) {
    return Optional.ofNullable(em.find(Magazine.class, id));
  }

  @Override
  public void deleteAll() {

  }
}
