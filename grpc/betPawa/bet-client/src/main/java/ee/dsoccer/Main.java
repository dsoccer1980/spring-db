package ee.dsoccer;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.BalanceServiceGrpc;
import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.DepositServiceGrpc;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import ee.dsoccer.bet.WithdrawServiceGrpc;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    BalanceServiceStub stub = BalanceServiceGrpc.newStub(channel);
    WithdrawServiceStub withdrawStub = WithdrawServiceGrpc.newStub(channel);
    DepositServiceStub depositStub = DepositServiceGrpc.newStub(channel);

    Semaphore semaphore = new Semaphore(-2);

    User user = User.newBuilder().setUserId(1).build();
    stub.balance(user, new StreamObserver<Balance>() {
      @Override
      public void onNext(Balance balance) {
        System.out.println(balance);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(Arrays.toString(throwable.getStackTrace()));
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Balance");
        semaphore.release();
      }
    });

    Withdraw withdraw = Withdraw.newBuilder().setUserId(1).setAmount(111).setCurrency(Currency.EUR).build();
    withdrawStub.withdraw(withdraw, new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(Arrays.toString(throwable.getStackTrace()));
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Withdraw");
        semaphore.release();
      }
    });

    Deposit deposit = Deposit.newBuilder().setUserId(1).setAmount(333).setCurrency(Currency.EUR).build();
    depositStub.deposit(deposit, new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {

      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(Arrays.toString(throwable.getStackTrace()));
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Deposit");
        semaphore.release();
      }
    });

    semaphore.acquire();
  }

}
