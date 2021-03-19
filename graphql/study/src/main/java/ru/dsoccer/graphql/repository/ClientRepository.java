package ru.dsoccer.graphql.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer.graphql.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

  Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
  Optional<Client> findByFirstName(String firstName);
}
