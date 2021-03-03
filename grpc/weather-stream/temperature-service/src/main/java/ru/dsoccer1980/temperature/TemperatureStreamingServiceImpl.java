package ru.dsoccer1980.temperature;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.weather.TemperatureStreamingServiceGrpc.TemperatureStreamingServiceImplBase;
import ru.dsoccer1980.weather.Weather.Coordinates;
import ru.dsoccer1980.weather.Weather.Temperature;

public class TemperatureStreamingServiceImpl extends TemperatureStreamingServiceImplBase {

  @Override
  public StreamObserver<Coordinates> observe(StreamObserver<Temperature> responseObserver) {
    return new StreamObserver<Coordinates>() {
      @Override
      public void onNext(Coordinates coordinates) {
        Temperature temperature = Temperature.newBuilder().setDegree(coordinates.getLatitude() + coordinates.getLongitude() + 1000).build();
        responseObserver.onNext(temperature);
      }

      @Override
      public void onError(Throwable throwable) {
        responseObserver.onError(throwable);
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
  }
}
