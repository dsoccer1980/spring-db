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

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    BalanceServiceStub balanceStub = BalanceServiceGrpc.newStub(channel);
    WithdrawServiceStub withdrawStub = WithdrawServiceGrpc.newStub(channel);
    DepositServiceStub depositStub = DepositServiceGrpc.newStub(channel);

    Semaphore semaphore = new Semaphore(0);

    Deposit deposit = Deposit.newBuilder().setUserId(1).setAmount(200).setCurrency(Currency.EUR).build();
    new DepositService().deposit(deposit, depositStub);

    Withdraw withdraw = Withdraw.newBuilder().setUserId(1).setAmount(50).setCurrency(Currency.EUR).build();
    new WithdrawService().withdraw(withdraw, withdrawStub);
    User user = User.newBuilder().setUserId(1).build();
    new BalanceService().balance(user, balanceStub);

    semaphore.acquire();
  }

}
