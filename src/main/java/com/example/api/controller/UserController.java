package com.example.api.controller;

import com.example.database.dto.UserDto;
import com.example.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody final UserDto userDto) {
    return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.OK);
  }
}
