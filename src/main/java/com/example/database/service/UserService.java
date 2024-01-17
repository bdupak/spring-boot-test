package com.example.database.service;

import com.example.database.dto.UserDto;
import com.example.database.entity.User;
import com.example.database.repository.UserRepository;
import com.example.exception.NotFoundException;
import com.example.mapper.UserMapper;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private UserMapper mapper;

  public UserDto saveUser(final UserDto userDto) {
    return mapper.convertToDto(repository.save(mapper.convertDtoToModel(userDto)));
  }

  public User findByUsername(final String username) throws NotFoundException {
    final User userFromDb = repository.findByUsername(username);
    if (Objects.isNull(userFromDb)) {
      throw new NotFoundException("User not found with username = " + username);
    }
    return userFromDb;
  }
}
