package ru.dsoccer1980.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Magazine extends AbstractBaseEntity {

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

}
