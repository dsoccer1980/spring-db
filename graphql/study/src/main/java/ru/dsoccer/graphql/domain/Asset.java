package ru.dsoccer.graphql.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
public class Asset {

  @Id
  @Column(length = 16)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  UUID id;
  String type;
  @ManyToOne
  @JoinColumn(name = "asset_id_key")
  BankAccount bankAccount;

  public Asset() {
  }

  public Asset(String type) {
    this.type = type;
  }
}
