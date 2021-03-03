package ee.dsoccer.repository;

import ee.dsoccer.bet.Bet.AmountCurrency;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.exception.BankException;

public class BankRepositoryImpl implements BankRepository {

  @Override
  public void deposit(Deposit deposit) throws BankException {
    //TODO
  }

  @Override
  public void withdraw(Withdraw withdraw) throws BankException {
     //TODO
  }

  @Override
  public Balance getBalance(User user) {  //TODO
    AmountCurrency eur = AmountCurrency.newBuilder().setCurrency(Currency.EUR).setAmount(100).build();
    AmountCurrency usd = AmountCurrency.newBuilder().setCurrency(Currency.USD).setAmount(200).build();
    AmountCurrency gbp = AmountCurrency.newBuilder().setCurrency(Currency.GBP).setAmount(0).build();
    return Balance.newBuilder()
        .setAmountCurrency(0, eur)
        .setAmountCurrency(1, usd)
        .setAmountCurrency(2, gbp)
        .build();
  }
}
