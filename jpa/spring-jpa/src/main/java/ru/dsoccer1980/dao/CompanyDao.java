package ru.dsoccer1980.dao;

import java.util.Optional;
import ru.dsoccer1980.domain.Company;

public interface CompanyDao {

  void insert(Company company);

  Optional<Company> getById(long id);

  void deleteAll();

}
