package ee.dsoccer.server;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceImplBase;
import ee.dsoccer.exception.BankException;
import ee.dsoccer.model.Account;
import ee.dsoccer.repository.BankRepository;
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
      Account account = repository.findByUserIdAndCurrency(withdraw.getUserId(), withdraw.getCurrency())
          .orElseThrow(() -> new BankException("Unknown currency"));
      if (account.getAmount() < withdraw.getAmount()) {
        throw new BankException("insufficient funds");
      }

      account.setAmount(account.getAmount() - withdraw.getAmount());
      repository.save(account);
      responseObserver.onNext(Empty.newBuilder().build());
      responseObserver.onCompleted();
    } catch (BankException e) {
      responseObserver.onError(e);
    }
  }

}
