package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.grpc.GreetingServiceImpl;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    Server server = NettyServerBuilder.forPort(8080)
        .addService(new GreetingServiceImpl())
        .build();

    server.start();
    System.out.println("Server started");
    server.awaitTermination();

  }

}
