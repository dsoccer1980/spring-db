package ru.dsoccer1980.repository;

import ru.dsoccer1980.model.User;

public interface UserRepository {

  User findByName(String username);
}
