package ru.dsoccer.graphql.resolver.bank.query;

import graphql.kickstart.tools.GraphQLResolver;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;
import ru.dsoccer.graphql.service.BankAccountService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientResolver implements GraphQLResolver<BankAccount> {

  private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  private final BankAccountService bankAccountService;

  public CompletableFuture<Client> client(BankAccount bankAccount) {

    return CompletableFuture.supplyAsync(() -> {
      log.info("Request client for bankAccount {} ", bankAccount.getId());

      Client clientA = Client.builder().firstName("Deniss").lastName("Love").middleNames(Arrays.asList("Leonid", "Gennady"))
          .build();
//      Client clientB = Client.builder().id(UUID.randomUUID()).firstName("Sasha").lastName("Love2").build();
//      clientA.setClient(clientB);
//      clientB.setClient(clientA);
      Client client = bankAccount.getClient();
      Set<Client> clientChildren = bankAccountService.getClientChildren(client);
      clientChildren.forEach(client::setClient);
      clientChildren.forEach(c -> bankAccountService.getClientChildren(c).forEach(c::setClient));
      //      client.setClient(clientA);
      return client;
    }, executorService);
  }

}
