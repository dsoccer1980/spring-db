package ee.dsoccer;

import ee.dsoccer.bet.BalanceServiceGrpc;
import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.DepositServiceGrpc;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import ee.dsoccer.bet.WithdrawServiceGrpc;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import ee.dsoccer.model.Round;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class ClientCreating {

  private static final int THREAD_COUNT = 4;
  private static final String TARGET_SERVER = "localhost:8080";

  private ExecutorService executorService;
  private Round round;
  private List<Consumer<Integer>> rounds;

  public ClientCreating() {
    ManagedChannel channel = NettyChannelBuilder.forTarget(TARGET_SERVER)
        .usePlaintext().build();
    makeRounds(channel);
    executorService = Executors.newFixedThreadPool(THREAD_COUNT);
  }

  public void run() throws InterruptedException {
    Semaphore semaphore = new Semaphore(0);

    List<CompletableFuture> futures = new ArrayList<>();
    for (int i = 0; i < 800; i++) {
      futures.add(CompletableFuture.runAsync(new ClientThread(i), executorService));
    }

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
        .thenRun(semaphore::release);

    semaphore.acquire();
    executorService.shutdownNow();

  }

  private void makeRounds(ManagedChannel channel) {
    BalanceServiceStub balanceStub = BalanceServiceGrpc.newStub(channel);
    WithdrawServiceStub withdrawStub = WithdrawServiceGrpc.newStub(channel);
    DepositServiceStub depositStub = DepositServiceGrpc.newStub(channel);
    round = new Round(balanceStub, withdrawStub, depositStub);
    Consumer<Integer> roundA = (userId) -> round.roundA(userId);
    Consumer<Integer> roundB = (userId) -> round.roundB(userId);
    Consumer<Integer> roundC = (userId) -> round.roundC(userId);
    rounds = Arrays.asList(roundA, roundB, roundC);
  }


  class ClientThread implements Runnable {

    private int userId;

    ClientThread(int userId) {
      this.userId = userId;
    }

    @Override
    public void run() {
      int roundNumber = ThreadLocalRandom.current().nextInt(3);
      rounds.get(roundNumber).accept(userId);
    }
  }

}
