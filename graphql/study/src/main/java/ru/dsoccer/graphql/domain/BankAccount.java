package ru.dsoccer.graphql.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@AllArgsConstructor
public class BankAccount {

  @Id
  @Column(length = 16)
//  @GeneratedValue(generator = "UUID")
//  @GenericGenerator(
//      name = "UUID",
//      strategy = "org.hibernate.id.UUIDGenerator"
//  )
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @OneToOne
  @JoinColumn(name = "client_id_key", referencedColumnName = "id")
  Client client;
  @Enumerated(EnumType.STRING)
  Currency currency;
  @OneToMany(mappedBy = "bankAccount")
  List<Asset> assets;
  LocalDate createdOn;
  ZonedDateTime createdAt;

  public BankAccount() {
  }

  @Override
  public String toString() {
    return "BankAccount{" +
        "id=" + id +
        ", currency=" + currency +
        ", createdOn=" + createdOn +
        ", createdAt=" + createdAt +
        '}';
  }
}
