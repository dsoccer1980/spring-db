package ru.dsoccer1980.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dsoccer1980.config.SpringJdbcTestConfiguration;
import ru.dsoccer1980.domain.Author;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringJdbcTestConfiguration.class})
public class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @BeforeEach
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
    void saveByAuthorWithId() {
        authorDao.insert(new Author(100L, "New Author"));
        assertThat(authorDao.getAll().size()).isEqualTo(4);
    }

    @Test
    void saveByName() {
        authorDao.insert("New Author");
        assertThat(authorDao.getAll().size()).isEqualTo(4);
    }

    @Test
    void saveExistAuthor() {
        assertThrows(DuplicateKeyException.class, () -> authorDao.insert("Author1"));
    }

    @Test
    void getById() {
        Author author = authorDao.getAll().get(0);
        long id = author.getId();
        assertThat(authorDao.getById(id).orElseThrow(() -> new UnsupportedOperationException())).isEqualTo(author);
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> authorDao.getById(-1L));
    }

    @Test
    void deleteById() {
        List<Author> authors = authorDao.getAll();
        int sizeBeforeDelete = authors.size();
        authorDao.deleteById(authors.get(0).getId());
        assertThat(authorDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void deleteByWrongId() {
        int sizeBeforeDelete = authorDao.getAll().size();
        authorDao.deleteById(-1L);
        assertThat(authorDao.getAll().size()).isEqualTo(sizeBeforeDelete);
    }

    @Test
    void findIdByName() {
        Long author1Id = authorDao.getAll().get(0).getId();
        Long getAuthor1Id = authorDao.getIdByName("Author1");
        assertThat(getAuthor1Id).isEqualTo(author1Id);
    }

    @Test
    void findIdByWrongName() {
        assertThat(authorDao.getIdByName("Wrong name")).isEqualTo(-1L);
    }

}
