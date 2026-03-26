package com.daroch.booking.exceptions;

public class BusinessException extends RuntimeException {

  // No-arg constructor
  public BusinessException() {
    super();
  }

  // Constructor with message
  public BusinessException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public BusinessException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public BusinessException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
