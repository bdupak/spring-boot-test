package com.example.utils;

import com.example.database.dto.ErrorDto;
import com.example.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorDto> badCredentialsHandler(BadCredentialsException exception) {
    LOGGER.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ErrorDto(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<ErrorDto> expiredJwtHandler(ExpiredJwtException exception) {
    LOGGER.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ErrorDto(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()), HttpStatus.UNAUTHORIZED);
  }
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorDto> expiredJwtHandler(AccessDeniedException exception) {
    LOGGER.error(exception.getMessage(), exception);
    return new ResponseEntity<>(
        new ErrorDto(HttpStatus.FORBIDDEN.value(), exception.getMessage()), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> genericExceptionHandler(Exception e) {
    LOGGER.error("Unexpected: ", e);

    return new ResponseEntity<>(ErrorDto.internalServerError(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
