package ee.dsoccer.server;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceImplBase;
import ee.dsoccer.exception.BankException;
import ee.dsoccer.repository.BankRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WithDrawServiceImpl extends WithdrawServiceImplBase {

  private final BankRepository repository;

  public WithDrawServiceImpl(BankRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public void withdraw(Withdraw withdraw, StreamObserver<Empty> responseObserver) {
    try {
      repository.withdraw(withdraw);
      responseObserver.onNext(Empty.newBuilder().build());
      responseObserver.onCompleted();
    } catch (BankException e) {
      System.err.println(e.getMessage());
      responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription(e.getMessage())));
    }
  }

}
