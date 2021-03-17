package ru.dsoccer.graphql.resolver.bank.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Currency;
import ru.dsoccer.graphql.domain.input.BankAccountInput;

@Slf4j
@Component
@RequiredArgsConstructor
@Validated
public class BankAccountMutation  implements GraphQLMutationResolver {

  private final Clock clock;

  public BankAccount createBankAccount(@Valid BankAccountInput input) {
    log.info("create bankAccount for input {}", input);
    return BankAccount.builder()
        .id(UUID.randomUUID())
        .currency(Currency.EUR)
        .createdOn(LocalDate.now(clock))
        .createdAt(ZonedDateTime.now(clock))
        .build();
  }

}
