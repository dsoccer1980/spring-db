package ru.dsoccer1980.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;
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
    Iterator<HelloResponse> responseIterator = stub.greeting(request);
    while (responseIterator.hasNext()) {
      System.out.println(responseIterator.next());
    }

    channel.shutdownNow();
  }

}
