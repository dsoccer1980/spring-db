package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

public interface CompanyDao {

  void insert(Company company);

  void insertWithPerson(Company company, Person person);

  Optional<Company> getById(long id);

  void deleteAll();

  Optional<Company> getCompanyWithPerson(Company company);

  List<Company> getAll();
}
