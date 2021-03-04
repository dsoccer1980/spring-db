package ee.dsoccer;

import ee.dsoccer.server.StartService;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Main {


  public static void main(String[] args) throws IOException, InterruptedException {
    ConfigurableApplicationContext context = SpringApplication.run(Main.class);
    context.getBean(StartService.class).start();
  }


}
