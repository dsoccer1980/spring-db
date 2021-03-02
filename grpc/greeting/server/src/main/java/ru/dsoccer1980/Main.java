package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.grpc.GreetingServiceImpl;
import ru.dsoccer1980.grpc.PersonStreamingServiceGrpc;
import ru.dsoccer1980.grpc.PersonStreamingServiceGrpc.PersonStreamingServiceStub;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    String host = "localhost";
    int personPort = 8082;

    PersonStreamingServiceStub personStreamingService = PersonStreamingServiceGrpc
        .newStub(NettyChannelBuilder.forAddress(host, personPort).usePlaintext().build());

    Server server = NettyServerBuilder.forPort(8080)
        .addService(new GreetingServiceImpl(personStreamingService))
        .build();


    server.start();
    System.out.println("Server started");
    server.awaitTermination();

  }

}
