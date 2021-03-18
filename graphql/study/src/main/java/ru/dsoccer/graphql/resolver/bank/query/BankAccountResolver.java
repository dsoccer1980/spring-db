package ru.dsoccer.graphql.resolver.bank.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Currency;
import ru.dsoccer.graphql.service.BankAccountService;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountResolver implements GraphQLQueryResolver {

  private final BankAccountService bankAccountService;

  public BankAccount bankAccount(UUID id) {
    log.info("Retrieving bank account: {}", id);
    return BankAccount.builder().id(id).currency(Currency.USD).build();
  }

  public BankAccount bankAccountByName(String firstName) {
    log.info("Retrieving bank account for: {}", firstName);
    return bankAccountService.get(firstName);
  }

  public List<BankAccount> bankAccounts() {
    log.info("Retrieving all bank accounts");
    return bankAccountService.findAll();
  }

}
