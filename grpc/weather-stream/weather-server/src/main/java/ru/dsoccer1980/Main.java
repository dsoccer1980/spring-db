package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.server.WeatherServiceImpl;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc.WindStreamingServiceStub;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    String host = "localhost";
    int personPort = 8082;

    WindStreamingServiceStub windStreamingService = WindStreamingServiceGrpc
        .newStub(NettyChannelBuilder.forAddress(host, personPort)
            .usePlaintext()
            .build());

    Server server = NettyServerBuilder.forPort(8080)
        .addService(new WeatherServiceImpl(windStreamingService))
        .build();

    server.start();
    System.out.println("Server started");
    server.awaitTermination();
  }

}
