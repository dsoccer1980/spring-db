package ee.dsoccer.server;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceImplBase;
import ee.dsoccer.bet.Bet.AmountCurrency;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Balance.Builder;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.model.Account;
import ee.dsoccer.repository.BankRepository;
import io.grpc.stub.StreamObserver;
import java.util.List;
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
      List<Account> accounts = repository.findByUserId(user.getUserId());
      accounts.forEach(account -> Balance.newBuilder()
          .addAmountCurrency(AmountCurrency.newBuilder().setAmount(account.getAmount()).setCurrency(account.getCurrency()).build()));
      Builder builder = Balance.newBuilder();
      for (Account account : accounts) {
        builder.addAmountCurrency(AmountCurrency.newBuilder().setAmount(account.getAmount()).setCurrency(account.getCurrency()));
      }
      responseObserver.onNext(builder.build());
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(e);
    }
  }

}
