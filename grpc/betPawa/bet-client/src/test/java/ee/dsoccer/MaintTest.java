package ee.dsoccer;

import java.util.Scanner;

public class MaintTest {

  public static void main(String[] args) throws InterruptedException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Type number of users");
    int usersNumber = scanner.nextInt();
    System.out.println("number of concurrent requests a user will make");
    int concurrentNumber = scanner.nextInt();
    System.out.println("number of rounds each thread is executing");
    int roundPerThread = scanner.nextInt();

    new ClientCreating().run(usersNumber, concurrentNumber, roundPerThread);
  }

}
