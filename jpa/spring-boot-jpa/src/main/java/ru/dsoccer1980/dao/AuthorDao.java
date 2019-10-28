package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import ru.dsoccer1980.domain.Author;

public interface AuthorDao {

    void insert(Author author);

    void insert(String name);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    Long getIdByName(String name);

    void deleteAll();

}
