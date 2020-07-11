package ru.dsoccer1980.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.dsoccer1980.domain.Author;

@DataJpaTest
public class TestEntityManagerTest {

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void test() {
    entityManager.persist(new Author("Denis"));
    entityManager.flush();
    Author author = entityManager.find(Author.class, 1L);
    assertThat(author.getName()).isEqualTo("Denis");

    Long id = entityManager.persistAndGetId(new Author("Sasha"), Long.class);
    Author author2 = entityManager.find(Author.class, id);
    assertThat(author2.getName()).isEqualTo("Sasha");
  }
}
