package ru.dsoccer.graphql.resolver.bank.query;

import graphql.kickstart.tools.GraphQLResolver;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dsoccer.graphql.domain.Asset;
import ru.dsoccer.graphql.domain.BankAccount;

@Slf4j
@Component
public class AssetResolver implements GraphQLResolver<BankAccount> {

  private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public CompletableFuture<List<Asset>> assets(BankAccount bankAccount) {
    return CompletableFuture.supplyAsync(() -> {
      log.info("Request asset for bankAccount {} ", bankAccount.getId());
      return Arrays.asList(new Asset(UUID.randomUUID()), new Asset(UUID.randomUUID()));
    }, executorService);
  }

}
