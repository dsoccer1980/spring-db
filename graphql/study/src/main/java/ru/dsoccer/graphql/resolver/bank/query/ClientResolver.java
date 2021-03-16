package ru.dsoccer.graphql.resolver.bank.query;

import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.BankAccount;
import ru.dsoccer.graphql.domain.Client;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

  public DataFetcherResult<Client> client(BankAccount bankAccount) {
    log.info("Request client for bankAccount {} ", bankAccount.getId());
    Client clientA = Client.builder().id(UUID.randomUUID()).firstName("Deniss").lastName("Love").build();
    Client clientB = Client.builder().id(UUID.randomUUID()).firstName("Sasha").lastName("Love2").build();
    clientA.setClient(clientB);
    clientB.setClient(clientA);

    return DataFetcherResult.<Client>newResult()
        .data(clientA)
        .error(new GenericGraphQLError("Could not get sub-client"))
        .build();
  }

}
