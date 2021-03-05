package ee.dsoccer.service;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import io.grpc.stub.StreamObserver;
import java.util.Arrays;

public class DepositService {

  public void deposit(Deposit deposit, DepositServiceStub depositStub) {
    depositStub.deposit(deposit, new StreamObserver<Empty>() {
      @Override
      public void onNext(Empty empty) {
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
        System.err.println(Arrays.toString(throwable.getStackTrace()));
      }

      @Override
      public void onCompleted() {
        System.out.println("completed Deposit");
      }
    });
  }

}
