package ee.dsoccer.server;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceImplBase;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.repository.BankRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceServiceImpl extends BalanceServiceImplBase {

  private final BankRepository repository;

  public BalanceServiceImpl(BankRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public void balance(User user, StreamObserver<Balance> responseObserver) {
    try {
      Balance balance = repository.getBalance(user);
      responseObserver.onNext(balance);
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

}
