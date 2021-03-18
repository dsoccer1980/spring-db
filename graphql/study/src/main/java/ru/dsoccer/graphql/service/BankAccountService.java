package ru.dsoccer.graphql.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;
import ru.dsoccer.graphql.repository.BankAccountRepository;
import ru.dsoccer.graphql.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class BankAccountService {

  private final ClientRepository clientRepository;
  private final BankAccountRepository bankAccountRepository;

  @Transactional
  public BankAccount save(String firstName, String lastName, BankAccount bankAccount) {
    Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName)
        .orElseGet(() -> clientRepository.save((Client.builder().firstName(firstName).lastName(lastName).build())));
    bankAccount.setClient(client);
    return bankAccountRepository.save(bankAccount);
  }


  @Transactional(readOnly = true)
  public BankAccount get(String firstName) {
    Client client = clientRepository.findByFirstName(firstName).orElseThrow(() -> new EntityNotFoundException("firstName not found"));
    System.out.println("---------" + client);
    return bankAccountRepository.findByClient(client).orElseThrow(() -> new EntityNotFoundException("Account not found"));
  }
}
