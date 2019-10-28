package ru.dsoccer1980.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dsoccer1980.domain.Author;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RowMapper<Author> authorRowMapper = (rs, i) -> new Author(rs.getLong("id"), rs.getString("name"));

    public AuthorDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insert(Author author) {
        if (author.getId() == null) {
            insert(author.getName());
        } else {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", author.getId());
            params.addValue("name", author.getName());
            namedParameterJdbcTemplate.update("INSERT INTO Author(id, name) VALUES(:id,:name)", params);
        }
    }

    @Override
    public void insert(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        namedParameterJdbcTemplate.update("INSERT INTO Author(name) VALUES(:name)", params);
    }

    @Override
    public Optional<Author> getById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject("SELECT * FROM Author WHERE id=:id", params, authorRowMapper));
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcTemplate.query("SELECT * FROM Author", authorRowMapper);
    }

    @Override
    public void deleteById(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM Author WHERE id=:id", params);
    }

    @Override
    public Long getIdByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);

        try {
            return namedParameterJdbcTemplate.queryForObject("SELECT id FROM Author WHERE name=:name", params, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return -1L;
        }
    }

    @Override
    public void deleteAll() {
        Map<String, Object> params = Collections.emptyMap();
        namedParameterJdbcTemplate.update("DELETE FROM Author", params);
    }
}
