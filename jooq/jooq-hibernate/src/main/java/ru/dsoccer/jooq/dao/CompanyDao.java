package ru.dsoccer.jooq.dao;

import java.util.List;
import java.util.Optional;
import ru.dsoccer.jooq.domain.Company;
import ru.dsoccer.jooq.domain.Person;

public interface CompanyDao {

  void insert(Company company);

  void insertWithPerson(Company company, Person person);

  Optional<Company> getById(long id);

  void deleteAll();

  Optional<Company> getCompanyWithPerson(Company company);

  List<Company> getAll();

  List<Company> getAllWithNamedGraph();

  List<Company> getAllWithGraph();

  List<Company> getCompaniesWithPersonMax(int count);

}
