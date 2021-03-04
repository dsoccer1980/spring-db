package ee.dsoccer.server;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceImplBase;
import ee.dsoccer.exception.BankException;
import ee.dsoccer.model.Account;
import ee.dsoccer.repository.BankRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositServiceImpl extends DepositServiceImplBase {

  private final BankRepository repository;

  public DepositServiceImpl(BankRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public void deposit(Deposit deposit, StreamObserver<Empty> responseObserver) {
    try {
      Account account = repository.findByUserIdAndCurrency(deposit.getUserId(), deposit.getCurrency())
          .orElse(new Account(deposit.getUserId(), 0, deposit.getCurrency()));
      account.setAmount(account.getAmount() + deposit.getAmount());
      repository.save(account);
      responseObserver.onNext(Empty.newBuilder().build());
      responseObserver.onCompleted();
    } catch (BankException e) {
      responseObserver.onError(e);
    }
  }

}
