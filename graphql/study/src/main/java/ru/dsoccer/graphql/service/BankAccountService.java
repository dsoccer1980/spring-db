package ru.dsoccer.graphql.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;
import ru.dsoccer.graphql.exception.GraphqlEntityNotFound;
import ru.dsoccer.graphql.repository.BankAccountRepository;
import ru.dsoccer.graphql.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class BankAccountService {

  private final ClientRepository clientRepository;
  private final BankAccountRepository bankAccountRepository;
  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  public BankAccount save(String firstName, String lastName, BankAccount bankAccount) {
    Client client = clientRepository.findByFirstNameAndLastName(firstName, lastName)
        .orElseGet(() -> clientRepository.save((Client.builder().firstName(firstName).lastName(lastName).build())));
    bankAccount.setClient(client);
    return bankAccountRepository.save(bankAccount);
  }


  @Transactional(readOnly = true)
  public BankAccount get(String firstName) {
    Client client = clientRepository.findByFirstName(firstName).orElseThrow(() -> new GraphqlEntityNotFound("firstName not found"));
    return bankAccountRepository.findByClient(client).orElseThrow(() -> new GraphqlEntityNotFound("Account not found"));
  }

  @Transactional(readOnly = true)
  public List<BankAccount> findAll() {
    return bankAccountRepository.findAll();
  }


  @Transactional
  public Set<Client> getClientChildren(Client client) {
    List<Client> clients = entityManager.createQuery("SELECT c from Client c JOIN FETCH c.children where c.id=:id", Client.class)
        .setParameter("id", client.getId())
        .getResultList();
    Set<Client> set = clients.stream().flatMap(client1 -> client1.getChildren().stream()).collect(Collectors.toSet());
    return set;
  }
}
