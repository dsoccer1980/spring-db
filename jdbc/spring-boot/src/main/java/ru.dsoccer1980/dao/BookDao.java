package ru.dsoccer1980.dao;

import ru.dsoccer1980.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<Book> getById(long id);

    void insert(Book book);

    void insert(String bookName, String authorName);

    List<Book> getAll();

    void deleteById(long id);

    List<Book> getByAuthorId(long id);

    void deleteAll();
}
