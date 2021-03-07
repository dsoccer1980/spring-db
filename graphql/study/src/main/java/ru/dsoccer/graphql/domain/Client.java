package ru.dsoccer.graphql.domain;


import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Client {
  UUID id;
  String firstName;
  List<String> middleNames;
  String lastName;
  Client client;

}
