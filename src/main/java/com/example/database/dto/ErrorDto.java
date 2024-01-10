package com.example.database.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

  private static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error";
  private int statusCode;
  private String message;

  public ErrorDto(final int statusCode, final String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public static ErrorDto internalServerError() {
    return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR_MSG);
  }
}
