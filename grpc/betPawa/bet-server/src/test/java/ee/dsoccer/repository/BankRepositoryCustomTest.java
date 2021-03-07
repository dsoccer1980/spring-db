package ee.dsoccer.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import ee.dsoccer.bet.Bet.AmountCurrency;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.exception.BankException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = NONE)
public class BankRepositoryCustomTest {

  @Autowired
  private BankRepository repository;

  @Test
  void deposit() {
    Deposit deposit = Deposit.newBuilder().setUserId(1).setAmount(100).setCurrency(Currency.USD).build();
    repository.deposit(deposit);
    Balance balance = repository.getBalance(User.newBuilder().setUserId(1).build());
    AmountCurrency amountCurrency = balance.getAmountCurrencyList().get(0);
    Assert.assertEquals(100, amountCurrency.getAmount());
    Assert.assertEquals(Currency.USD, amountCurrency.getCurrency());
  }

  @Test
  void withdraw() {
    Deposit deposit = Deposit.newBuilder().setUserId(1).setAmount(300).setCurrency(Currency.USD).build();
    repository.deposit(deposit);
    Withdraw withdraw = Withdraw.newBuilder().setUserId(1).setAmount(100).setCurrency(Currency.USD).build();
    repository.withdraw(withdraw);
    Balance balance = repository.getBalance(User.newBuilder().setUserId(1).build());
    AmountCurrency amountCurrency = balance.getAmountCurrencyList().get(0);
    Assert.assertEquals(200, amountCurrency.getAmount());
    Assert.assertEquals(Currency.USD, amountCurrency.getCurrency());
  }

  @Test
  void withdrawWithoutDeposit() {
    Withdraw withdraw = Withdraw.newBuilder().setUserId(1).setAmount(100).setCurrency(Currency.USD).build();
    assertThrows(BankException.class, () -> repository.withdraw(withdraw));
  }
}
