package ru.dsoccer1980.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

@Repository
public class BookDaoJdbc implements BookDao {

    private final String QUERY_SELECT = "SELECT *, a.name as author_name FROM Book b LEFT JOIN Author a ON b.author_id=a.id";
    private NamedParameterJdbcTemplate jdbcTemplate;
    private AuthorDao authorDao;
    private SimpleJdbcInsert insertAuthor;
    private RowMapper<Book> bookRowMapper = (rs, i) ->
            new Book(
                    rs.getLong("id"),
                    rs.getString("name"),
                    new Author(rs.getLong("author_id"), rs.getString("author_name")));

    public BookDaoJdbc(NamedParameterJdbcTemplate jdbcTemplate, AuthorDao authorDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorDao = authorDao;
        insertAuthor = new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("author")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(QUERY_SELECT + " WHERE b.id=:id", params, bookRowMapper));
    }

    @Override
    public void insert(Book book) {
        insert(book.getName(), book.getAuthor().getName());
    }

    @Override
    public void insert(String bookName, String authorName) {
        long authorId = authorDao.getIdByName(authorName);
        if (authorId == -1) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", authorName);
            Number number = insertAuthor.executeAndReturnKey(params);
            authorId = number.longValue();
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", bookName);
        params.addValue("author_id", authorId);

        jdbcTemplate.update("INSERT INTO Book(name, author_id) VALUES(:name, :author_id)", params);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(QUERY_SELECT, bookRowMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcTemplate.update("DELETE FROM Book WHERE id=:id", params);
    }

    @Override
    public List<Book> getByAuthorId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcTemplate.query(QUERY_SELECT + " WHERE a.id=:id", params, bookRowMapper);
    }

    @Override
    public void deleteAll() {
        Map<String, Object> params = Collections.emptyMap();
        jdbcTemplate.update("DELETE FROM Book", params);
    }


}
