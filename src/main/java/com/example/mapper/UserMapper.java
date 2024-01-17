package com.example.mapper;

import com.example.database.dto.UserDto;
import com.example.database.entity.User;
import com.example.utils.PasswordUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  PasswordUtils passwordUtils;

  public UserDto convertToDto(final User user) {
    return objectMapper.convertValue(user, UserDto.class);
  }

  public User convertDtoToModel(final UserDto userDto) {
    User user = objectMapper.convertValue(userDto, User.class);
    user.setSalt(passwordUtils.generateSalt());
    user.setPassword(passwordUtils.encodePassword(userDto.getPassword(), user.getSalt()));
    return user;
  }
}
