package ru.dsoccer1980.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class})
@ActiveProfiles("test")
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @BeforeEach
    void save() {
        bookDao.deleteAll();
        Author author1 = new Author("Author1");
        bookDao.insert(new Book("Name1", author1));
        bookDao.insert(new Book("Name2", author1));
        bookDao.insert(new Book("Name3", new Author("Author2")));
    }

    @Test
    void getAll() {
        List<Book> all = bookDao.getAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void saveBookWithId() {
        bookDao.insert(new Book(100L, "New Book", new Author("any name")));
        assertThat(bookDao.getAll().size()).isEqualTo(4);
    }

    @Test
    void getById() {
        Book book = bookDao.getAll().get(0);
        long id = book.getId();
        assertThat(bookDao.getById(id).orElseThrow(() -> new RuntimeException())).isEqualTo(book);
    }

    @Test
    void getByWrongId() {
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(-1L));
    }

    @Test
    void deleteById() {
        List<Book> books = bookDao.getAll();
        int sizeBeforeDelete = books.size();
        bookDao.deleteById(books.get(0).getId());
        assertThat(bookDao.getAll().size()).isEqualTo(sizeBeforeDelete - 1);
    }

    @Test
    void deleteByWrongId() {
        int sizeBeforeDelete = bookDao.getAll().size();
        bookDao.deleteById(-1L);
        assertThat(bookDao.getAll().size()).isEqualTo(sizeBeforeDelete);
    }

    @Test
    void getByAuthorId() {
        Book book = bookDao.getAll().get(0);
        Author author = book.getAuthor();
        List<Book> byAuthorId = bookDao.getByAuthorId(author.getId());
        assertThat(byAuthorId.size()).isEqualTo(2);
        assertThat(byAuthorId.get(0).getAuthor()).isEqualTo(author);
    }


}
