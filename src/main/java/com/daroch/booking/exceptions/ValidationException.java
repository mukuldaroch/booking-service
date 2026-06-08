package com.daroch.booking.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends BusinessException {

  public ValidationException(String message) {
    super("VALIDATION_ERROR", message, HttpStatus.BAD_REQUEST);
  }
}
