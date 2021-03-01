package ru.dsoccer1980.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.dsoccer1980.grpc.GreetingServiceGrpc;
import ru.dsoccer1980.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;

public class Main {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
    HelloRequest request = HelloRequest.newBuilder().setName("Deniss").build();
    HelloResponse response = stub.greeting(request);
    System.out.println(response);

    channel.shutdownNow();
  }

}
