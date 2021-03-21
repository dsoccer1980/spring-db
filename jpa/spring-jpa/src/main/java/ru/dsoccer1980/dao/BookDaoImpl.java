package ru.dsoccer1980.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;
import ru.dsoccer1980.domain.Book;

@Repository
@Transactional(readOnly = true)
public class BookDaoImpl implements BookDao {

  private final AuthorDao authorDao;
  @PersistenceContext
  private EntityManager em;

  public BookDaoImpl(AuthorDao authorDao) {
    this.authorDao = authorDao;
  }

  @Override
  public Optional<Book> getById(long id) {
    return Optional.ofNullable(em.find(Book.class, id));
  }

  @Override
  @Transactional
  public void insert(Book book) {
    em.merge(book);
  }

  @Override
  @Transactional
  public void insert(String bookName, String authorName) {
    Long authorIdByName = authorDao.getIdByName(authorName);
    Author author;
    if (authorIdByName != null) {
      author = authorDao.getById(authorIdByName).orElseThrow(() -> new RuntimeException());
    } else {
      author = new Author(authorName);
    }
    Book book = new Book(bookName, author);
    em.merge(book);
  }

  @Override
  public List<Book> getAll() {
    TypedQuery<Book> query = em.createQuery("SELECT b from Book b", Book.class);
    return query.getResultList();
  }

  @Override
  @Transactional
  public void deleteById(long id) {
    em.createQuery("DELETE FROM Book b WHERE b.id=:id")
        .setParameter("id", id)
        .executeUpdate();
  }

  @Override
  public List<Book> getByAuthorId(long id) {
    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.id=:id", Book.class)
        .setParameter("id", id);
    return query.getResultList();
  }

  @Override
  @Transactional
  public void deleteAll() {
    Query query = em.createQuery("DELETE FROM Book");
    query.executeUpdate();
  }

  @Override
  public List<Book> getBookByAuthorIds(long... ids) {
    List<Long> idsList = Arrays.stream(ids).boxed().collect(Collectors.toList());
    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.id IN (:ids)", Book.class)
        .setParameter("ids", idsList);
    EntityGraph<Book> entityGraph = em.createEntityGraph(Book.class);
    entityGraph.addAttributeNodes("author");
    query.setHint(QueryHints.FETCHGRAPH, entityGraph);
    return query.getResultList();
  }
}
