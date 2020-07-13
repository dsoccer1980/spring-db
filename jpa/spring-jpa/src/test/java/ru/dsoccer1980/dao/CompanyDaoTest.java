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
import ru.dsoccer1980.domain.Person;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
public class CompanyDaoTest {

  @Autowired
  private CompanyDao companyDao;

  @Autowired
  private PersonDao personDao;


  @BeforeEach
  @Transactional
  void save() {
    companyDao.deleteAll();
    Company company = new Company("Company1", new ContactPerson("Denis", "Vorosnin", "+7981916"));
    companyDao.insert(company);
    Company company2 = new Company("Company2", new ContactPerson("Denis2", "Vorosnin2", "+79819161"));
    companyDao.insert(company2);

    personDao.deleteAll();
    Person denisV = new Person("DenisV");
    personDao.insert(denisV);
    personDao.addCompany(denisV, company);
    personDao.insert(new Person("SashaF"));

  }

  // @BeforeEach
  @Transactional
  void save2() {
    companyDao.deleteAll();
    personDao.deleteAll();
    Company company = new Company("Company1", new ContactPerson("Denis", "Vorosnin", "+7981916"));
    Person denisV = new Person("DenisV");
    //denisV.setCompany(company);
    companyDao.insertWithPerson(company, denisV);


  }


  @Test
  void getById() {
    Company company = companyDao.getById(1).get();
    System.out.println(company);
    assertThat(company.getName()).isEqualTo("Company1");
    assertThat(company.getContactPerson().getFirstName()).isEqualTo("Denis");
    System.out.println(companyDao.getCompanyWithPerson(company));
  }

  @Test
  void getAll() {
    System.out.println("-------------------");
    System.out.println(companyDao.getAll());
  }

  @Test
  void getAllWithGraph() {
    System.out.println("-------------------");
    System.out.println(companyDao.getAllWithGraph());
  }


  @Test
  void getPersonName() {
    System.out.println("-------------------");
    personDao.testQuery(3L, 4L);
//    Person person1 = entityManager.find(Person.class, 1);
//    Person person2 = entityManager.find(Person.class, 2);
//    person1.getCompany().getName();
  }

  @Test
  void getCompanyPhone() {
    System.out.println(companyDao.getCompanyPhone());
  }


}
