package ee.dsoccer.server;

import static org.mockito.Mockito.doNothing;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Deposit;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceImplBase;
import ee.dsoccer.repository.BankRepository;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import({DepositServiceImpl.class})
public class DepositServiceImplTest {

  @Autowired
  private DepositServiceImplBase depositService;

  @MockBean
  private BankRepository bankRepository;

  @Test
  void deposit() {
    int userId = 4;
    Deposit deposit = Deposit.newBuilder().setUserId(userId).setAmount(100).setCurrency(Currency.USD).build();
    doNothing().when(bankRepository).deposit(deposit);

    StreamRecorder<Empty> depositObserver = StreamRecorder.create();
    depositService.deposit(deposit, depositObserver);
    Assert.assertNull(depositObserver.getError());
  }

}
