package ru.dsoccer1980.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Company.class)
public abstract class Company_ {

  public static volatile ListAttribute<Company, Person> persons;
  public static volatile SingularAttribute<Company, String> name;
}
