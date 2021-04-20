package ru.dsoccer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer.model.Person;
import ru.dsoccer.repository.PersonRepository;

@RestController
public class MainController {

  @Autowired
  private PersonRepository personRepository;

  @GetMapping("/person/{name}")
  public Person findPersonByName(String name) {
    return personRepository.findByName(name).orElse(new Person());
  }
}
