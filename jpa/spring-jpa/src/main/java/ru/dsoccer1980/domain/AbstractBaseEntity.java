package ru.dsoccer1980.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractBaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected long id;
}
