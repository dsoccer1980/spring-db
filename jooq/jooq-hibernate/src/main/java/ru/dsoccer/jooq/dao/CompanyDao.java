package ru.dsoccer.jooq.dao;

import java.util.List;

public interface CompanyDao {

  List<ru.dsoccer.jooq.domain.Company> getCompaniesWithPersonsUsingJOOQ();

  List<ru.dsoccer.jooq.domain.Company> getCompaniesWithPersonsUsingJOOQ2();
}
