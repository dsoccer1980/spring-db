package ru.dsoccer1980.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column(name = "company_name")
  private String name;

  @Embedded
  private ContactPerson contactPerson;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Person> persons;

  public Company(String name, ContactPerson contactPerson) {
    this.name = name;
    this.contactPerson = contactPerson;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", contactPerson=" + contactPerson +
        '}';
  }
}
