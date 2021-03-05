package ee.dsoccer.repository;

import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Account, Long>, BankRepositoryCustom {

  Optional<Account> findByUserIdAndCurrency(int userId, Currency currency);

  List<Account> findByUserId(int userId);
}
