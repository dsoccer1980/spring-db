package ru;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.temperature.TemperatureStreamingServiceImpl;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    Server server = NettyServerBuilder.forPort(8083)
        .addService(new TemperatureStreamingServiceImpl())
        .build();
    server.start();
    System.out.println("temperature service started");
    server.awaitTermination();
  }

}
