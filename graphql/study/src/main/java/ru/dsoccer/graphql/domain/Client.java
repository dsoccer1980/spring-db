package ru.dsoccer.graphql.domain;


import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Client {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  UUID id;
  String firstName;
  @Transient
  List<String> middleNames;
  String lastName;
  @Transient
  Client client;

  @OneToOne(mappedBy = "client")
  BankAccount bankAccount;

  public Client() {
  }
}
