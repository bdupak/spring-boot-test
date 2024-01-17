package com.example.database.service;

import com.example.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) {
    try {
      return userService.findByUsername(username);
    } catch (NotFoundException e) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
