package ru.dsoccer1980.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Company {

  @Id
  @GeneratedValue
  private long id;

  @Column(name = "company_name")
  private String name;

  @Embedded
  private ContactPerson contactPerson;

  public Company(String name, ContactPerson contactPerson) {
    this.name = name;
    this.contactPerson = contactPerson;
  }
}
