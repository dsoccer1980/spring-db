package ru.dsoccer1980.dao;

import java.util.List;
import java.util.Optional;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.Person;

public interface PersonDao {

  void insert(Person person);

  void updatePersonWithName(long id, String name);

  void addCompanyById(Person person, long id);

  void addCompanyByPersonIdAndCompanyId(long personId, long companyId);

  void addCompany(Person person, Company company);

  Optional<Person> getById(long id);

  void deleteAll();

  void deleteById(long id);

  void delete(Person person);

  Person getReference(long id);

  List<Person> getAll();

  List<Person> getAllWithNamedQuery();

  List<Person> getPersonsWithCompany();

  List<Person> getPersonsWithCompanyEntityGraph();

  void testQuery(Long id1, Long id2);

  String doNativeQuery(long id);

  void doWorkNativeSql();

  String doReturnWorkNativeSql(long id);

  List<Company> getCompaniesWithPersons();
}
