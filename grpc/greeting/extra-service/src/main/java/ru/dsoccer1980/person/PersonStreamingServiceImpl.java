package ru.dsoccer1980.person;

import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.Person;
import ru.dsoccer1980.grpc.GreetingServiceOuterClass.Phone;
import ru.dsoccer1980.grpc.PersonStreamingServiceGrpc.PersonStreamingServiceImplBase;

public class PersonStreamingServiceImpl extends PersonStreamingServiceImplBase {

  Map<String, String> map = new HashMap<>();

  {
    map.put("1", "Denis");
    map.put("2", "Sasha");
    map.put("3", "Love");
  }


  @Override
  public StreamObserver<Phone> observe(StreamObserver<Person> responseObserver) {
    return new StreamObserver<Phone>() {
      @Override
      public void onNext(Phone phone) {
        String name = map.getOrDefault(phone.getNumber(), "Unknown");
        Person person = Person.newBuilder().setName(name).build();
        responseObserver.onNext(person);
      }

      @Override
      public void onError(Throwable throwable) {
        System.err.println(throwable.toString());
      }

      @Override
      public void onCompleted() {
        System.out.println("completed person service");
      }
    };
  }
}
