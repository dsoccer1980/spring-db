package ru.dsoccer.graphql.resolver.bank.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Currency;
import ru.dsoccer.graphql.domain.input.BankAccountInput;

@Slf4j
@Component
public class BankAccountMutation  implements GraphQLMutationResolver {

  public BankAccount createBankAccount(BankAccountInput input) {
    log.info("create bankAccount for input {}", input);
    return BankAccount.builder().id(UUID.randomUUID()).currency(Currency.EUR).build();
  }

}
