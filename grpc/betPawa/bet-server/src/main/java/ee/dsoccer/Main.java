package ee.dsoccer;

import ee.dsoccer.repository.BankRepository;
import ee.dsoccer.repository.BankRepositoryImpl;
import ee.dsoccer.server.BalanceServiceImpl;
import ee.dsoccer.server.DepositServiceImpl;
import ee.dsoccer.server.WithDrawServiceImpl;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

    BankRepository bankRepository = new BankRepositoryImpl();

    Server server = NettyServerBuilder.forPort(8080)
        .addService(new BalanceServiceImpl(bankRepository))
        .addService(new DepositServiceImpl(bankRepository))
        .addService(new WithDrawServiceImpl(bankRepository))
        .build();

    server.start();
    System.out.println("Server started");
    server.awaitTermination();

  }

}
