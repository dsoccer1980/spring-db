package ee.dsoccer.server;

import com.google.protobuf.Empty;
import ee.dsoccer.bet.Bet.Currency;
import ee.dsoccer.bet.Bet.Withdraw;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceImplBase;
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
@Import({WithDrawServiceImpl.class})
@MockBean(BankRepository.class)
public class WithdrawServiceImplTest {

  @Autowired
  private WithdrawServiceImplBase withdrawService;

  @Test
  void withdraw() {
    int userId = 4;
    Withdraw withdraw = Withdraw.newBuilder().setUserId(userId).setAmount(100).setCurrency(Currency.USD).build();
    StreamRecorder<Empty> withdrawObserver = StreamRecorder.create();
    withdrawService.withdraw(withdraw, withdrawObserver);
    Assert.assertNull(withdrawObserver.getError());
  }


}
