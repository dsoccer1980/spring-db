package ru.dsoccer1980.client;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.Semaphore;
import ru.dsoccer1980.grpc.GreetingServiceGrpc;
import ru.dsoccer1980.grpc.GreetingServiceGrpc.GreetingServiceStub;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);

    Semaphore semaphore = new Semaphore(0);

    StreamObserver<HelloRequest> greetingStream = stub.greeting(new StreamObserver<HelloResponse>() {
      @Override
      public void onNext(HelloResponse helloResponse) {
        System.out.println(helloResponse);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
        semaphore.release();
      }

      @Override
      public void onCompleted() {
        System.out.println("completed");
        semaphore.release();

      }
    });

    for (int i = 0; i < 10; i++) {
      HelloRequest request = HelloRequest.newBuilder().setName(i + "").build();
      greetingStream.onNext(request);
    }

    semaphore.acquire();
  }

}
