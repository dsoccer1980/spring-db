package ru.dsoccer1980.grpc;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  @Override
  public StreamObserver<HelloRequest> greeting(StreamObserver<HelloResponse> responseObserver) {
    return new StreamObserver<HelloRequest>() {
      @Override
      public void onNext(HelloRequest request) {
        HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
            .setGreeting("Hello from server " + ", " + request.getName()).build();
        responseObserver.onNext(response);
        responseObserver.onNext(response);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
  }

}
