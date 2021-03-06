package ee.dsoccer.model;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceStub;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.User;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceStub;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceStub;
import ee.dsoccer.service.BalanceService;
import ee.dsoccer.service.DepositService;
import ee.dsoccer.service.WithdrawService;

public class Round {

  private BalanceServiceStub balanceStub;
  private WithdrawServiceStub withdrawStub;
  private DepositServiceStub depositStub;

  public Round(BalanceServiceStub balanceStub, WithdrawServiceStub withdrawStub, DepositServiceStub depositStub) {
    this.balanceStub = balanceStub;
    this.withdrawStub = withdrawStub;
    this.depositStub = depositStub;
  }

  public void roundA(int userId) {
    Deposit.Builder depositBuilder = Deposit.newBuilder().setUserId(userId);
    Withdraw.Builder withdrawBuilder = Withdraw.newBuilder().setUserId(userId);
    User user = User.newBuilder().setUserId(userId).build();

    new DepositService().deposit(depositBuilder.setAmount(100).setCurrency(Currency.USD).build(), depositStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(200).setCurrency(Currency.USD).build(), withdrawStub);
    new DepositService().deposit(depositBuilder.setAmount(100).setCurrency(Currency.EUR).build(), depositStub);
    new BalanceService().balance(user, balanceStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.USD).build(), withdrawStub);
    new BalanceService().balance(user, balanceStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.USD).build(), withdrawStub);
  }

  public void roundB(int userId) {
    Deposit.Builder depositBuilder = Deposit.newBuilder().setUserId(userId);
    Withdraw.Builder withdrawBuilder = Withdraw.newBuilder().setUserId(userId);

    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.GBP).build(), withdrawStub);
    new DepositService().deposit(depositBuilder.setAmount(300).setCurrency(Currency.GBP).build(), depositStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.GBP).build(), withdrawStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.GBP).build(), withdrawStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.GBP).build(), withdrawStub);
  }

  public void roundC(int userId) {
    Deposit.Builder depositBuilder = Deposit.newBuilder().setUserId(userId);
    Withdraw.Builder withdrawBuilder = Withdraw.newBuilder().setUserId(userId);
    User user = User.newBuilder().setUserId(userId).build();

    new BalanceService().balance(user, balanceStub);
    new DepositService().deposit(depositBuilder.setAmount(100).setCurrency(Currency.USD).build(), depositStub);
    new DepositService().deposit(depositBuilder.setAmount(100).setCurrency(Currency.USD).build(), depositStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(100).setCurrency(Currency.USD).build(), withdrawStub);
    new DepositService().deposit(depositBuilder.setAmount(100).setCurrency(Currency.USD).build(), depositStub);
    new BalanceService().balance(user, balanceStub);
    new WithdrawService().withdraw(withdrawBuilder.setAmount(200).setCurrency(Currency.USD).build(), withdrawStub);
  }


}
