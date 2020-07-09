package ru.dsoccer1980.dao;

import java.util.Optional;
import ru.dsoccer1980.domain.Magazine;

public interface MagazineDao {

  void insert(Magazine magazine);

  Optional<Magazine> getById(long id);

  void deleteAll();

}
