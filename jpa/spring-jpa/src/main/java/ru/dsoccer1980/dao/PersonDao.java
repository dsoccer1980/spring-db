package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

public interface PersonDao {

  void insert(Person person);

  void addCompany(Person person, Company company);

  Optional<Person> getById(long id);

  void deleteAll();

  List<Person> getAll();

  void testQuery(Long id1, Long id2);
}
