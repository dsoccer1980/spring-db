package ee.dsoccer.model;

import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.Bet.Withdraw;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "userid")
  private int userId;

  private int amount;

  @Enumerated(EnumType.STRING)
  private Currency currency;

  public Account(int userId, int amount, Currency currency) {
    this.userId = userId;
    this.amount = amount;
    this.currency = currency;
  }

  public Account(long id, int userId, int amount, Currency currency) {
    this.userId = userId;
    this.amount = amount;
    this.currency = currency;
  }

  public Account() {
  }

  public static Account depositToAccount(Deposit deposit) {
    return new Account(deposit.getUserId(), deposit.getAmount(), deposit.getCurrency());
  }

  public static Account withdrawToAccount(Withdraw withdraw) {
    return new Account(withdraw.getUserId(), withdraw.getAmount(), withdraw.getCurrency());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
