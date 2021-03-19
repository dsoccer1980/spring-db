package ru.dsoccer.graphql.domain;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@AllArgsConstructor
public class Client {

  @Id
  @Column(length = 16)
//  @GeneratedValue(generator = "UUID")
//  @GenericGenerator(
//      name = "UUID",
//      strategy = "org.hibernate.id.UUIDGenerator"
//  )
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  String firstName;
  @Transient
  List<String> middleNames;
  String lastName;
  //  @ManyToMany(fetch = FetchType.EAGER)
//  @JoinTable(
//      name = "client_join",
//      joinColumns = @JoinColumn(name = "client_id"),
//      inverseJoinColumns = @JoinColumn(name = "client2_id"))
  @Transient
  Client client;
  @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
  @Transient
  BankAccount bankAccount;
  @ManyToMany
  private Set<Client> children = new HashSet<>();

  public Client() {
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
