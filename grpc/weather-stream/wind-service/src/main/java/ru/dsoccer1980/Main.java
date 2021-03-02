package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.service.WindStreamingServiceImpl;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    Server server = NettyServerBuilder.forPort(8082)
        .addService(new WindStreamingServiceImpl())
        .build();
    server.start();
    System.out.println("wind service started");
    server.awaitTermination();
  }

}
