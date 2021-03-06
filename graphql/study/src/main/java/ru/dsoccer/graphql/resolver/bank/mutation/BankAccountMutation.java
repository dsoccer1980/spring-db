package ru.dsoccer.graphql.resolver.bank.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Currency;
import ru.dsoccer.graphql.domain.input.BankAccountInput;
import ru.dsoccer.graphql.service.BankAccountService;

@Slf4j
@Component
@RequiredArgsConstructor
@Validated
public class BankAccountMutation implements GraphQLMutationResolver {


  private final BankAccountService bankAccountService;
  private final Clock clock;

  public BankAccount createBankAccount(@Valid BankAccountInput input) {
    log.info("create bankAccount for input {}", input);

    BankAccount bankAccount = BankAccount.builder()
        .currency(Currency.EUR)
        .createdOn(LocalDate.now(clock))
        .createdAt(ZonedDateTime.now(clock))
        .build();
    bankAccountService.save(input.getFirstName(), input.getLastName(), bankAccount);
    return bankAccount;
  }

}
