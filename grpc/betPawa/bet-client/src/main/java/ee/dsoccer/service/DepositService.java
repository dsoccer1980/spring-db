package ee.dsoccer.service;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class DepositService {

  public void deposit(Deposit deposit, DepositServiceStub depositStub) {
    Semaphore semaphore = new Semaphore(0);
    StreamObserver<Empty> completedDeposit = new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
        System.err.println(Arrays.toString(throwable.getStackTrace()));
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Deposit");
        semaphore.release();
      }
    };
    depositStub.deposit(deposit, completedDeposit);
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
