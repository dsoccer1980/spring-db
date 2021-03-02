package ru.dsoccer1980.service;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.weather.Weather.Coordinates;
import ru.dsoccer1980.weather.Weather.Wind;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc.WindStreamingServiceImplBase;

public class WindStreamingServiceImpl extends WindStreamingServiceImplBase {

  @Override
  public StreamObserver<Coordinates> observe(StreamObserver<Wind> responseObserver) {
    return new StreamObserver<Coordinates>() {
      @Override
      public void onNext(Coordinates coordinates) {
        Wind wind = Wind.newBuilder().setSpeed(coordinates.getLatitude() + coordinates.getLongitude()).build();
        responseObserver.onNext(wind);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        System.out.println("WindStreamingServiceImpl completed");
      }
    };
  }
}
