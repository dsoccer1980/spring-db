package ru.dsoccer1980.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {

    private final String QUERY_SELECT = "SELECT *, a.name as author_name  FROM Book b LEFT JOIN Author a ON b.author_id=a.id";
    private NamedParameterJdbcOperations jdbcOperations;
    private AuthorDao authorDao;
    private RowMapper<Book> bookRowMapper = (rs, i) ->
            new Book(
                    rs.getLong("id"),
                    rs.getString("name"),
                    new Author(rs.getLong("author_id"), rs.getString("author_name")));

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations, AuthorDao authorDao) {
        this.jdbcOperations = jdbcOperations;
        this.authorDao = authorDao;
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(jdbcOperations.queryForObject(QUERY_SELECT + " WHERE b.id=:id", params, bookRowMapper));
    }

    @Override
    public void insert(Book book) {
        insert(book.getName(), book.getAuthor().getName());
    }

    @Override
    public void insert(String bookName, String authorName) {
        long authorId = authorDao.getIdByName(authorName);
        if (authorId == -1) {
            authorDao.insert(authorName);
            authorId = authorDao.getIdByName(authorName);
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", bookName);
        params.addValue("author_id", authorId);

        jdbcOperations.update("INSERT INTO Book(name, author_id) VALUES(:name, :author_id)", params);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query(QUERY_SELECT, bookRowMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM Book WHERE id=:id", params);
    }

    @Override
    public List<Book> getByAuthorId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.query(QUERY_SELECT + " WHERE a.id=:id", params, bookRowMapper);
    }

    @Override
    public void deleteAll() {
        Map<String, Object> params = Collections.emptyMap();
        jdbcOperations.update("DELETE FROM Book", params);
    }


}
