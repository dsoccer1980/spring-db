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
import ru.dsoccer1980.domain.Magazine;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
public class MagazineDaoTest {

  @Autowired
  private MagazineDao magazineDao;

  @BeforeEach
  @Transactional
  void save() {
    magazineDao.deleteAll();
    magazineDao.insert(new Magazine("Magazine1"));
    magazineDao.insert(new Magazine("Author2"));
  }


  @Test
  void getById() {
    Magazine magazine = magazineDao.getById(1).get();
    System.out.println(magazine);
    assertThat(magazine.getName()).isEqualTo("Magazine1");
  }


}
