package com.daroch.booking.exceptions;

public class EventServiceFailedException extends RuntimeException {

  // No-arg constructor
  public EventServiceFailedException() {
    super();
  }

  // Constructor with message
  public EventServiceFailedException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public EventServiceFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public EventServiceFailedException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public EventServiceFailedException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
