package ru.dsoccer.graphql.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;
import ru.dsoccer.graphql.domain.Currency;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

  public BankAccount bankAccount(UUID id) {
    log.info("Retrieving bank account: {}", id);
    Client clientA = Client.builder().id(UUID.randomUUID()).firstName("Deniss").lastName("Love").build();
    Client clientB = Client.builder().id(UUID.randomUUID()).firstName("Sasha").lastName("Love2").build();
    clientA.setClient(clientB);
    clientB.setClient(clientA);

    return BankAccount.builder().id(id).currency(Currency.USD).client(clientA).build();
  }


}
