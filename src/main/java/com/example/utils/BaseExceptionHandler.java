package com.example.utils;

import com.example.database.dto.ErrorDto;
import com.example.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException exception) {
    LOGGER.error(exception.getMessage(), exception);
    final HttpStatus httpStatus = exception.getHttpStatus();
    return new ResponseEntity<>(
        new ErrorDto(httpStatus.value(), exception.getMessage()), httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> genericExceptionHandler(Exception e) {
    LOGGER.error("Unexpected: ", e);

    return new ResponseEntity<>(ErrorDto.internalServerError(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
