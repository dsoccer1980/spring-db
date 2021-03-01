package ru.dsoccer1980.grpc;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  @Override
  public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

    HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
        .setGreeting("Hello from server").build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
