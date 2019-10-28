package ru.dsoccer1980.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.domain.Company;

@Transactional
public interface CompanyDao extends JpaRepository<Company, Long> {


}
