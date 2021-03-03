package ru.dsoccer1980.server;

import io.grpc.stub.StreamObserver;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import ru.dsoccer1980.weather.TemperatureStreamingServiceGrpc.TemperatureStreamingServiceStub;
import ru.dsoccer1980.weather.Weather.Coordinates;
import ru.dsoccer1980.weather.Weather.WeatherRequest;
import ru.dsoccer1980.weather.Weather.WeatherResponse;
import ru.dsoccer1980.weather.Weather.WeatherResponse.Builder;
import ru.dsoccer1980.weather.WeatherStreamingServiceGrpc.WeatherStreamingServiceImplBase;
import ru.dsoccer1980.weather.WindStreamingServiceGrpc.WindStreamingServiceStub;

public class WeatherServiceImpl extends WeatherStreamingServiceImplBase {

  private final WindStreamingServiceStub windStreamingService;
  private final TemperatureStreamingServiceStub temperatureStreamingService;

  public WeatherServiceImpl(WindStreamingServiceStub windStreamingService,
      TemperatureStreamingServiceStub temperatureStreamingService) {
    this.windStreamingService = windStreamingService;
    this.temperatureStreamingService = temperatureStreamingService;
  }

  @Override
  public StreamObserver<WeatherRequest> observer(StreamObserver<WeatherResponse> responseObserver) {
    AutoClosableLock lock = new AutoClosableLock(new ReentrantLock());

    StreamObserver<Coordinates> windObserver = windStreamingService.observe(getNewObserver(responseObserver, Builder::setWind, lock));
    StreamObserver<Coordinates> temperatureObserver = temperatureStreamingService
        .observe(getNewObserver(responseObserver, Builder::setTemperature, lock));
    List<StreamObserver<Coordinates>> observers = Arrays.asList(windObserver, temperatureObserver);

    return new StreamObserver<WeatherRequest>() {
      @Override
      public void onNext(WeatherRequest weatherRequest) {
        observers.forEach(obs -> obs.onNext(weatherRequest.getCoordinates()));
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

  private <T> StreamObserver<T> getNewObserver(
      StreamObserver<WeatherResponse> responseObserver,
      BiFunction<Builder, T, Builder> convert,
      AutoClosableLock observerLock) {

    return new StreamObserver<T>() {
      @Override
      public void onNext(T value) {
        WeatherResponse weatherResponse = convert.apply(WeatherResponse.newBuilder(), value).build();
        try (AutoClosableLock ignored = observerLock.lock()) {
          responseObserver.onNext(weatherResponse);
        }
      }

      @Override
      public void onError(Throwable throwable) {
        try (AutoClosableLock ignored = observerLock.lock()) {
          responseObserver.onError(throwable);
        }
      }

      @Override
      public void onCompleted() {
        try (AutoClosableLock ignored = observerLock.lock()) {
          responseObserver.onCompleted();
        }
      }
    };
  }

  private static final class AutoClosableLock implements AutoCloseable {

    private final Lock lock;

    private AutoClosableLock(Lock lock) {
      this.lock = lock;
    }

    AutoClosableLock lock() {
      lock.lock();
      return this;
    }

    @Override
    public void close() {
      lock.unlock();
    }
  }

}
