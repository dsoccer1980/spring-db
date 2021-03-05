package ee.dsoccer.service;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.User;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;

public class BalanceService {

  public void balance(User user, BalanceServiceStub balanceStub) {
    balanceStub.balance(user, new StreamObserver<Balance>() {
      @Override
      public void onNext(Balance balance) {
        System.out.println(balance);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
        System.err.println(Arrays.toString(throwable.getStackTrace()));
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Balance");
      }
    });
  }

}
