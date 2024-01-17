package com.example.database.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDto {
  private long id;
  private String username;
  private String password;
  private String salt;
  private String role;
  private Boolean isDeleted = Boolean.FALSE;
}
