package ru.dsoccer.graphql.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

  Optional<BankAccount> findByClient(Client client);
}
