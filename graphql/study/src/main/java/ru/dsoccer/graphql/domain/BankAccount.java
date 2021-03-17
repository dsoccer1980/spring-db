package ru.dsoccer.graphql.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BankAccount {

  UUID id;
  Client client;
  Currency currency;
  List<Asset> assets;
  LocalDate createdOn;
  ZonedDateTime createdAt;
}
