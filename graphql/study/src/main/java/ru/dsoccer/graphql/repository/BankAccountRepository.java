package ru.dsoccer.graphql.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

  Optional<BankAccount> findByClient(Client client);
}
