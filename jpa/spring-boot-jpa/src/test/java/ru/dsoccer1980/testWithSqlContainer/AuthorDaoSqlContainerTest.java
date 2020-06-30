package ru.dsoccer1980.testWithSqlContainer;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.dsoccer1980.PostgreSQLContainerShared;
import ru.dsoccer1980.dao.AuthorDao;
import ru.dsoccer1980.dao.AuthorDaoImpl;
import ru.dsoccer1980.domain.Author;


@DataJpaTest
@Import(AuthorDaoImpl.class)
@ActiveProfiles("test")
public class AuthorDaoSqlContainerTest {

  @ClassRule
  private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER = PostgreSQLContainerShared
      .getInstance();

  static {
    POSTGRE_SQL_CONTAINER.start();
  }

  @Autowired
  private AuthorDao authorDao;

  @BeforeEach
  @Transactional
  void save() {
    authorDao.deleteAll();
    authorDao.insert(new Author("Author1"));
    authorDao.insert(new Author("Author2"));
    authorDao.insert(new Author("Author3"));
  }

  @Test
  void getAll() {
    assertThat(authorDao.getAll().size()).isEqualTo(3);
  }

  @Test
  void getById() {
    Author author = authorDao.getAll().get(0);
    long id = author.getId();
    assertThat(authorDao.getById(id).orElseThrow(RuntimeException::new)).isEqualTo(author);
  }


}
