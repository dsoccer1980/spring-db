package ru.dsoccer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

  Optional<Person> findByName(String name);

}
