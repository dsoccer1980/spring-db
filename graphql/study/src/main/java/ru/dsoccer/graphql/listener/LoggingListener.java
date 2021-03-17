package ru.dsoccer.graphql.listener;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingListener implements GraphQLServletListener {

  private final Clock clock;

  @Override
  public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
    log.info("Request started");
    Instant startTime = Instant.now(clock);
    return new RequestCallback() {
      @Override
      public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
        //no-op
      }

      @Override
      public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        //no-op
      }

      @Override
      public void onFinally(HttpServletRequest request, HttpServletResponse response) {
        log.info("Request completed for time {}", Duration.between(startTime, Instant.now(clock)));
      }
    };
  }
}
