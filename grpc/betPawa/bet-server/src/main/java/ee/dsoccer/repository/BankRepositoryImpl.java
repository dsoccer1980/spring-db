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
    System.out.println("Deposit " + deposit);
  }

  @Override
  public void withdraw(Withdraw withdraw) throws BankException {
     //TODO
    System.out.println("Withdraw " + withdraw);
  }

  @Override
  public Balance getBalance(User user) {  //TODO
    AmountCurrency eur = AmountCurrency.newBuilder().setCurrency(Currency.EUR).setAmount(100).build();
    AmountCurrency usd = AmountCurrency.newBuilder().setCurrency(Currency.USD).setAmount(200).build();
    AmountCurrency gbp = AmountCurrency.newBuilder().setCurrency(Currency.GBP).setAmount(300).build();
    return Balance.newBuilder()
        .addAmountCurrency(usd)
        .addAmountCurrency(eur)
        .addAmountCurrency(gbp)
        .build();
  }
}
