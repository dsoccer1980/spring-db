package ru.dsoccer.jooq.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedEntityGraph(name = Company.GRAPH_WITH_PERSONS, attributeNodes = {@NamedAttributeNode("persons")})
public class Company {

  public static final String GRAPH_WITH_PERSONS = "Company.withPersons";
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Person> persons;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
//  @Embedded
//  private ContactPerson contactPerson;
  @Column(name = "company_name")
  private String name;

  public Company(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
