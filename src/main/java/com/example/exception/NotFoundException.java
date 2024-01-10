package com.example.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

  private static final long serialVersionUID = 7013085441196324202L;

  public NotFoundException(String message) {
    super(message);
  }

  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
