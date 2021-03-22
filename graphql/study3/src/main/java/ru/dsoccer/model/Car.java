package ru.dsoccer.model;

import io.leangen.graphql.annotations.GraphQLQuery;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GraphQLQuery(name = "id", description = "A id")
  private Long id;

  @GraphQLQuery(name = "name", description = "A name")
  private String name;

}
