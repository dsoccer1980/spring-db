package ru.dsoccer1980;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode({Mode.SingleShotTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Threads(1)
@Fork(2)
@Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
public class BenchMarking {


  @Benchmark
  public void doWork30() {
    fib(30);
  }

  @Benchmark
  public void doWork31() {
    fib(31);
  }

  @Benchmark
  public void doWork40() {
    fib(40);
  }

  private int fib(int n) {
    if (n <= 1) {
      return n;
    }
    int result = 0;
    int num1 = 0;
    int num2 = 1;
    for (int i = 2; i <= n; i++) {
      result = num1 + num2;
      num1 = num2;
      num2 = result;
    }
    return result;
  }

}
