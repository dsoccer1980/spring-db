package ee.dsoccer.server;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceImplBase;
import ee.dsoccer.exception.BankException;
import ee.dsoccer.repository.BankRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImpl extends DepositServiceImplBase {

  private final BankRepository repository;

  public DepositServiceImpl(BankRepository repository) {
    this.repository = repository;
  }

  @Override
  public void deposit(Deposit deposit, StreamObserver<Empty> responseObserver) {
    try {
      repository.deposit(deposit);
      responseObserver.onNext(Empty.newBuilder().build());
      responseObserver.onCompleted();
    } catch (BankException e) {
      responseObserver.onError(e);
    }
  }

}
