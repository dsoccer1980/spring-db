package ru.dsoccer1980.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Author;

@Repository
@Transactional(readOnly = true)
public interface AuthorDao extends JpaRepository<Author, Long> {

   Optional<Long> findIdByName(String name);

}
