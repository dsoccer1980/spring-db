package ru.dsoccer1980.grpc;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  @Override
  public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

    for (int i = 0; i < 10; i++) {
      HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
          .setGreeting("Hello from server " + i + ", " + request.getName()).build();
      responseObserver.onNext(response);
    }

    responseObserver.onCompleted();
  }
}
