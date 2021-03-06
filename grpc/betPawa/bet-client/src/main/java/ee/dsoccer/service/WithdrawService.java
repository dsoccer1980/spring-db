package ee.dsoccer.service;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.Semaphore;

public class WithdrawService {

  public void withdraw(Withdraw withdraw, WithdrawServiceStub withdrawStub) {
    Semaphore semaphore = new Semaphore(0);
    withdrawStub.withdraw(withdraw, new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(Status.fromThrowable(throwable).getDescription());
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Withdraw");
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
