package ee.dsoccer.server;

import ee.dsoccer.bet.BalanceServiceGrpc.BalanceServiceImplBase;
import ee.dsoccer.bet.DepositServiceGrpc.DepositServiceImplBase;
import ee.dsoccer.bet.WithdrawServiceGrpc.WithdrawServiceImplBase;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService {

  @Autowired
  private BalanceServiceImplBase balanceService;

  @Autowired
  private DepositServiceImplBase depositService;

  @Autowired
  private WithdrawServiceImplBase withdrawService;

  public void start() throws IOException, InterruptedException {
    Server server = NettyServerBuilder.forPort(8080)
        .addService(balanceService)
        .addService(depositService)
        .addService(withdrawService)
        .build();

    server.start();
    System.out.println("Server started");
    server.awaitTermination();
  }

}
