package ee.dsoccer.repository;

import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.exception.BankException;

public interface BankRepository {

  void deposit(Deposit deposit) throws BankException;

  void withdraw(Withdraw withdraw) throws BankException;

  Balance getBalance(User user);
}
