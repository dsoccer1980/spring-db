package ee.dsoccer.service;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.User;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class BalanceService {

  public void balance(User user, BalanceServiceStub balanceStub) {
    Semaphore semaphore = new Semaphore(0);
    balanceStub.balance(user, new StreamObserver<Balance>() {
      @Override
      public void onNext(Balance balance) {
        System.out.println("UserId " + user.getUserId() + ": " + balance);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
        System.err.println(Arrays.toString(throwable.getStackTrace()));
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Balance");
        semaphore.release();
      }
    });
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
