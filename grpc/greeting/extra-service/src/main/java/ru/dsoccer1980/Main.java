package ru.dsoccer1980;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import ru.dsoccer1980.person.PersonStreamingServiceImpl;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    int personPort = 8082;

    Server personServer = NettyServerBuilder.forPort(personPort)
        .addService(new PersonStreamingServiceImpl())
        .build();
    personServer.start();

    personServer.awaitTermination();

  }

}
