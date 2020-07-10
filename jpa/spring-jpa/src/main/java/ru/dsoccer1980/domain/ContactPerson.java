package ru.dsoccer1980.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContactPerson {

  @Column(name = "contact_first_name")
  private String firstName;
  private String lastName;
  private String phone;
}
