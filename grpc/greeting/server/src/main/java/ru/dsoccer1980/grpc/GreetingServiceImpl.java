package ru.dsoccer1980.grpc;

import io.grpc.stub.StreamObserver;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloRequest;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.HelloResponse;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.Person;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.Phone;
import ru.dsoccer1980.grpc.PersonStreamingServiceGrpc.PersonStreamingServiceStub;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

  private final PersonStreamingServiceStub personStreamingService;

  public GreetingServiceImpl(PersonStreamingServiceStub personStreamingService) {
    this.personStreamingService = personStreamingService;
  }


  @Override
  public StreamObserver<HelloRequest> greeting(StreamObserver<HelloResponse> responseObserver) {
    StreamObserver<Phone> phoneStream = personStreamingService.observe(new StreamObserver<Person>() {
      @Override
      public void onNext(Person person) {
        HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
            .setGreeting("Hello from server " + ", " + person.getName()).build();
        responseObserver.onNext(response);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        System.out.println("completed phoneStreamObserver");

      }
    });

    return new StreamObserver<HelloRequest>() {
      @Override
      public void onNext(HelloRequest request) {
        Phone phone = Phone.newBuilder().setNumber(request.getName()).build();
        phoneStream.onNext(phone);
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
