package ru.dsoccer1980.server;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.weather.Weather.Coordinates;
import ru.dsoccer1980.weather.Weather.WeatherRequest;
import ru.dsoccer1980.weather.Weather.WeatherResponse;
import ru.dsoccer1980.weather.Weather.Wind;
import ru.dsoccer1980.weather.WeatherStreamingServiceGrpc.WeatherStreamingServiceImplBase;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc.WindStreamingServiceStub;

public class WeatherServiceImpl extends WeatherStreamingServiceImplBase {

  private final WindStreamingServiceStub windStreamingService;

  public WeatherServiceImpl(WindStreamingServiceStub windStreamingService) {
    this.windStreamingService = windStreamingService;
  }

  @Override
  public StreamObserver<WeatherRequest> observer(StreamObserver<WeatherResponse> responseObserver) {
    StreamObserver<Coordinates> coordinatesStreamObserver = windStreamingService.observe(new StreamObserver<Wind>() {
      @Override
      public void onNext(Wind wind) {
        WeatherResponse weatherResponse = WeatherResponse.newBuilder().setWind(wind).build();
        responseObserver.onNext(weatherResponse);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        System.out.println("coordinatesStreamObserver completed");
      }
    });

    return new StreamObserver<WeatherRequest>() {
      @Override
      public void onNext(WeatherRequest weatherRequest) {
        coordinatesStreamObserver.onNext(weatherRequest.getCoordinates());
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        System.out.println("WeatherServiceImpl completed");
      }
    };
  }
}
