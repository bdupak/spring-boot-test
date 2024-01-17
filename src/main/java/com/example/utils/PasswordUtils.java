package com.example.utils;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PasswordUtils {
  @Autowired
  private PasswordEncoder passwordEncoder;

  public String generateSalt(){
    final char startChar = 'a';
    final SecureRandom random = new SecureRandom();
    StringBuilder builder = new StringBuilder();
    boolean isUpperCase;
    char letter;
    for (int i = 0; i < 64; i++) {
      isUpperCase = random.nextBoolean();
      letter = (char) (random.nextInt(26) + startChar);
      builder.append(isUpperCase ? Character.toUpperCase(letter) : letter);
    }
    return builder.toString();
  }

  public String encodePassword(final String password, final String salt) {
    return passwordEncoder.encode(password + salt);
  }

}
