package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.server.WeatherServiceImpl;
import ru.dsoccer1980.weather.TemperatureStreamingServiceGrpc;
import ru.dsoccer1980.weather.TemperatureStreamingServiceGrpc.TemperatureStreamingServiceStub;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc.WindStreamingServiceStub;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    String host = "localhost";
    int windPort = 8082;
    int temperaturePort = 8083;

    WindStreamingServiceStub windStreamingService = WindStreamingServiceGrpc
        .newStub(NettyChannelBuilder.forAddress(host, windPort)
            .usePlaintext()
            .build());

    TemperatureStreamingServiceStub temperatureStreamingService = TemperatureStreamingServiceGrpc
        .newStub(NettyChannelBuilder.forAddress(host, temperaturePort)
            .usePlaintext()
            .build());

    Server server = NettyServerBuilder.forPort(8080)
        .addService(new WeatherServiceImpl(windStreamingService, temperatureStreamingService))
        .build();

    server.start();
    System.out.println("Server started");
    server.awaitTermination();
  }

}
