package ru.dsoccer1980.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import org.springframework.stereotype.Service;

@Service
public class ThrowingExceptionsService {

  public void throwUnsupportedOperationException() {
    throw new UnsupportedOperationException("Operation not supportes");
  }

  public void throwIllegalArgumentException() {
    throw new IllegalArgumentException("Illegal argument");
  }

  public void throwIllegalStateException() {
    throw new IllegalStateException("Illegal state exception");
  }

  public void throwUncheckedIOException() {
    throw new UncheckedIOException("unchecked io  exception", new IOException("my"));
  }
}
