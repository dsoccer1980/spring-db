package ru.dsoccer.graphql.resolver.bank.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Currency;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {

  public BankAccount bankAccount(UUID id) {
    log.info("Retrieving bank account: {}", id);

    return BankAccount.builder().id(id).currency(Currency.USD).build();
  }


}
