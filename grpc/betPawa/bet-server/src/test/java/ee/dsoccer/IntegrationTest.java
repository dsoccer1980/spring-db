package ee.dsoccer;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceImplBase;
import ee.dsoccer.bet.Bet.AmountCurrency;
import ee.dsoccer.bet.Bet.Balance;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceImplBase;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceImplBase;
import ee.dsoccer.repository.BankRepository;
import ee.dsoccer.server.BalanceServiceImpl;
import ee.dsoccer.server.DepositServiceImpl;
import ee.dsoccer.server.WithDrawServiceImpl;
import io.grpc.Status;
import io.grpc.internal.testing.StreamRecorder;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrationTest {

  private BalanceServiceImplBase balanceService;
  private WithdrawServiceImplBase withdrawService;
  private DepositServiceImplBase depositService;

  @Autowired
  private BankRepository bankRepository;

  @BeforeEach
  public void setup() {
    balanceService = new BalanceServiceImpl(bankRepository);
    withdrawService = new WithDrawServiceImpl(bankRepository);
    depositService = new DepositServiceImpl(bankRepository);
  }

  @Test
  void test() {
    int userId = 4;
    User user = User.newBuilder().setUserId(userId).build();

    //1.
    Withdraw withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(200).setCurrency(Currency.USD).build();
    StreamRecorder<Empty> withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    assertEquals("insufficient funds", Status.fromThrowable(withdrawObserver.getError()).getDescription());

    //2.
    Deposit deposit = Deposit.newBuilder().setUserId(userId).setAmount(100).setCurrency(Currency.USD).build();
    StreamRecorder<Empty> depositObserver = StreamRecorder.create();
    depositService.deposit(deposit, depositObserver);

    //3.
    StreamRecorder<Balance> balanceObserver = StreamRecorder.create();
    balanceService.balance(user, balanceObserver);
    List<Balance> results = balanceObserver.getValues();
    assertEquals(1, results.size());
    AmountCurrency amountCurrency = results.get(0).getAmountCurrencyList().get(0);
    assertEquals(100, amountCurrency.getAmount());
    assertEquals("USD", amountCurrency.getCurrency().name());

    //4.
    withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(200).setCurrency(Currency.USD).build();
    withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    assertEquals("insufficient funds", Status.fromThrowable(withdrawObserver.getError()).getDescription());

    //5.
    deposit = Deposit.newBuilder().setUserId(userId).setAmount(100).setCurrency(Currency.EUR).build();
    depositObserver = StreamRecorder.create();
    depositService.deposit(deposit, depositObserver);

    //6.
    balanceObserver = StreamRecorder.create();
    balanceService.balance(user, balanceObserver);
    results = balanceObserver.getValues();
    assertEquals(1, results.size());
    List<AmountCurrency> amountCurrencyList = results.get(0).getAmountCurrencyList();
    assertEquals(2, amountCurrencyList.size());
    for (AmountCurrency balance : amountCurrencyList) {
      switch (balance.getCurrency()) {
        case EUR:
          assertEquals(100, balance.getAmount());
          break;
        case USD:
          assertEquals(100, balance.getAmount());
          break;
        default:
          fail("unknown currency");
      }
    }

    //7.
    withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(200).setCurrency(Currency.USD).build();
    withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    assertEquals("insufficient funds", Status.fromThrowable(withdrawObserver.getError()).getDescription());

    //8.
    deposit = Deposit.newBuilder().setUserId(userId).setAmount(100).setCurrency(Currency.USD).build();
    depositObserver = StreamRecorder.create();
    depositService.deposit(deposit, depositObserver);

    //9.
    balanceObserver = StreamRecorder.create();
    balanceService.balance(user, balanceObserver);
    results = balanceObserver.getValues();
    assertEquals(1, results.size());
    amountCurrencyList = results.get(0).getAmountCurrencyList();
    assertEquals(2, amountCurrencyList.size());
    for (AmountCurrency balance : amountCurrencyList) {
      switch (balance.getCurrency()) {
        case EUR:
          assertEquals(100, balance.getAmount());
          break;
        case USD:
          assertEquals(200, balance.getAmount());
          break;
        default:
          fail("unknown currency");
      }
    }

    //10.
    withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(200).setCurrency(Currency.USD).build();
    withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    assertNull(withdrawObserver.getError());

    //11.
    balanceObserver = StreamRecorder.create();
    balanceService.balance(user, balanceObserver);
    results = balanceObserver.getValues();
    assertEquals(1, results.size());
    amountCurrencyList = results.get(0).getAmountCurrencyList();
    assertEquals(2, amountCurrencyList.size());
    for (AmountCurrency balance : amountCurrencyList) {
      switch (balance.getCurrency()) {
        case EUR:
          assertEquals(100, balance.getAmount());
          break;
        case USD:
          assertEquals(0, balance.getAmount());
          break;
        default:
          fail("unknown currency");
      }
    }

    //12.
    withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(200).setCurrency(Currency.USD).build();
    withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    assertEquals("insufficient funds", Status.fromThrowable(withdrawObserver.getError()).getDescription());
  }

}
