package ee.dsoccer.repository;

import ee.dsoccer.bet.Bet.AmountCurrency;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Balance.Builder;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.exception.BankException;
import ee.dsoccer.model.Account;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BankRepositoryCustomImpl implements BankRepositoryCustom {

  @Autowired
  private BankRepository repository;

  @Override
  @Transactional
  public void deposit(Deposit deposit) throws BankException {
    verifyCurrency(deposit.getCurrency().name());
    Account account = repository.findByUserIdAndCurrency(deposit.getUserId(), deposit.getCurrency())
        .orElse(new Account(deposit.getUserId(), 0, deposit.getCurrency()));
    account.setAmount(account.getAmount() + deposit.getAmount());
    repository.save(account);
  }

  @Override
  @Transactional
  public void withdraw(Withdraw withdraw) throws BankException {
    verifyCurrency(withdraw.getCurrency().name());
    Account account = repository.findByUserIdAndCurrency(withdraw.getUserId(), withdraw.getCurrency())
        .orElseThrow(() -> new BankException("Unknown currency"));
    if (account.getAmount() < withdraw.getAmount()) {
      throw new BankException("insufficient funds");
    }
    account.setAmount(account.getAmount() - withdraw.getAmount());
    repository.save(account);
  }

  @Override
  @Transactional
  public Balance getBalance(User user) {
    List<Account> accounts = repository.findByUserId(user.getUserId());
    accounts.forEach(account -> Balance.newBuilder()
        .addAmountCurrency(AmountCurrency.newBuilder().setAmount(account.getAmount()).setCurrency(account.getCurrency()).build()));
    Builder builder = Balance.newBuilder();
    for (Account account : accounts) {
      builder.addAmountCurrency(AmountCurrency.newBuilder().setAmount(account.getAmount()).setCurrency(account.getCurrency()));
    }
    return builder.build();
  }

  private void verifyCurrency(String currency) {
    if (Arrays.stream(Currency.values()).noneMatch(c -> c.name().equals(currency))) {
      throw new BankException("Unknown currency");
    }
  }
}
