package ru.dsoccer1980.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private NamedParameterJdbcOperations jdbcOperations;
    private RowMapper<Author> authorRowMapper = (rs, i) -> new Author(rs.getLong("id"), rs.getString("name"));

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void insert(Author author) {
        if (author.getId() == null) {
            insert(author.getName());
        } else {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", author.getId());
            params.addValue("name", author.getName());

            jdbcOperations.update("INSERT INTO Author(id, name) VALUES(:id,:name)", params);
        }
    }

    @Override
    public void insert(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);

        jdbcOperations.update("INSERT INTO Author(name) VALUES(:name)", params);
    }

    @Override
    public Optional<Author> getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(jdbcOperations.queryForObject("SELECT * FROM Author WHERE id=:id", params, authorRowMapper));
    }

    @Override
    public List<Author> getAll() {
        return jdbcOperations.query("SELECT * FROM Author", authorRowMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("DELETE FROM Author WHERE id=:id", params);
    }

    @Override
    public Long getIdByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return jdbcOperations.queryForObject("SELECT id FROM Author WHERE name=:name", params, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return -1L;
        }
    }

    @Override
    public void deleteAll() {
        Map<String, Object> params = Collections.emptyMap();
        jdbcOperations.update("DELETE FROM Author", params);
    }


}
