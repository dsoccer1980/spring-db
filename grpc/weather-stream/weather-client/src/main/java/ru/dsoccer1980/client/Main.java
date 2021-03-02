package ru.dsoccer1980.client;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.Semaphore;
import ru.dsoccer1980.weather.Weather.Coordinates;
import ru.dsoccer1980.weather.Weather.WeatherRequest;
import ru.dsoccer1980.weather.Weather.WeatherResponse;
import ru.dsoccer1980.weather.WeatherStreamingServiceGrpc;
import ru.dsoccer1980.weather.WeatherStreamingServiceGrpc.WeatherStreamingServiceStub;


public class Main {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = NettyChannelBuilder.forTarget("localhost:8080")
        .usePlaintext().build();

    WeatherStreamingServiceStub stub = WeatherStreamingServiceGrpc.newStub(channel);

    Semaphore semaphore = new Semaphore(0);

    StreamObserver<WeatherRequest> greetingStream = stub.observer(new StreamObserver<WeatherResponse>() {
      @Override
      public void onNext(WeatherResponse weatherResponse) {
        System.out.println(weatherResponse);
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
      Coordinates coordinates = Coordinates.newBuilder()
          .setLatitude(i)
          .setLongitude(i)
          .build();
      WeatherRequest request = WeatherRequest.newBuilder().setCoordinates(coordinates).build();
      greetingStream.onNext(request);
    }

    semaphore.acquire();
  }

}
