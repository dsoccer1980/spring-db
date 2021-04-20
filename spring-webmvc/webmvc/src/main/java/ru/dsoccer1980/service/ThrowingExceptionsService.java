package ru.dsoccer1980.service;

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
}
