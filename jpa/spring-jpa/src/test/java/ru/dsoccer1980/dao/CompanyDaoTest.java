package ru.dsoccer1980.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dsoccer1980.config.SpringTestConfiguration;
import ru.dsoccer1980.domain.Company;
import ru.dsoccer1980.domain.ContactPerson;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
public class CompanyDaoTest {

  @Autowired
  private CompanyDao companyDao;

  @BeforeEach
  @Transactional
  void save() {
    companyDao.deleteAll();
    companyDao.insert(new Company("Company1", new ContactPerson("Denis", "Vorosnin", "+7981916")));
    companyDao.insert(new Company("Company2", new ContactPerson("Denis2", "Vorosnin2", "+79819161")));
  }


  @Test
  void getById() {
    Company company = companyDao.getById(1).get();
    System.out.println(company);
    assertThat(company.getName()).isEqualTo("Company1");
    assertThat(company.getContactPerson().getFirstName()).isEqualTo("Denis");
  }


}
