package ee.dsoccer;

import ee.dsoccer.bet.BalanceServiceGrpc;
import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.DepositServiceGrpc;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import ee.dsoccer.bet.WithdrawServiceGrpc;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import ee.dsoccer.service.BalanceService;
import ee.dsoccer.service.DepositService;
import ee.dsoccer.service.WithdrawService;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    BalanceServiceStub balanceStub = BalanceServiceGrpc.newStub(channel);
    WithdrawServiceStub withdrawStub = WithdrawServiceGrpc.newStub(channel);
    DepositServiceStub depositStub = DepositServiceGrpc.newStub(channel);

    Semaphore semaphore = new Semaphore(0);

    Deposit deposit = Deposit.newBuilder().setUserId(3).setAmount(100).setCurrency(Currency.GBP).build();
    new DepositService().deposit(deposit, depositStub);

    TimeUnit.SECONDS.sleep(2);

    Withdraw withdraw = Withdraw.newBuilder().setUserId(3).setAmount(40).setCurrency(Currency.GBP).build();
    new WithdrawService().withdraw(withdraw, withdrawStub);

    TimeUnit.SECONDS.sleep(1);
    User user = User.newBuilder().setUserId(3).build();
    new BalanceService().balance(user, balanceStub);

    semaphore.acquire();
  }

}
