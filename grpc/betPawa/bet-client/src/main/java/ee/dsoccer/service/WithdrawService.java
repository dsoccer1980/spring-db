package ee.dsoccer.service;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;

public class WithdrawService {

  public void withdraw(Withdraw withdraw, WithdrawServiceStub withdrawStub) {
    withdrawStub.withdraw(withdraw, new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(Arrays.toString(throwable.getStackTrace()));
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Withdraw");
      }
    });
  }

}
