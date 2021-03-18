package ru.dsoccer.graphql.domain.input;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BankAccountInput {

  @NotBlank
  String firstName;
  @NotBlank
  String lastName;
}
