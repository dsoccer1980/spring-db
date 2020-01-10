package ru.dsoccer1980.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Book;

@Repository
@Transactional(readOnly = true)
public interface BookDao extends JpaRepository<Book, Long> {

}
