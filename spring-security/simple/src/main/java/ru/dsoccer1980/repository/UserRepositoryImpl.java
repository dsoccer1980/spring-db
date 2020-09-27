package ru.dsoccer1980.repository;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Override
  public User findByName(String username) {
    return new User(1, "denis", "$2y$12$gd7YpDujHC7lnbf.cFRyAu8uThmRCGr0cxIwt0UsaYvl6BHPeig0i");
  }
}
