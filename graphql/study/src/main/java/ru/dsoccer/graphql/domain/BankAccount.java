package ru.dsoccer.graphql.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BankAccount {

  UUID id;
  Client client;
  Currency currency;
}
