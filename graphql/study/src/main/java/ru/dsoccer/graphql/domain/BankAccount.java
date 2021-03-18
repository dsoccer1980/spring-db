package ru.dsoccer.graphql.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Builder
@Data
@Entity
@AllArgsConstructor
public class BankAccount {

  @Id
  @Column(length = 16)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  UUID id;
  @OneToOne
  @JoinColumn(name = "bank_account_id_key", referencedColumnName = "id")
  Client client;
  @Enumerated(EnumType.STRING)
  Currency currency;
  @Transient
  List<Asset> assets;
  LocalDate createdOn;
  ZonedDateTime createdAt;

  public BankAccount() {
  }
}
