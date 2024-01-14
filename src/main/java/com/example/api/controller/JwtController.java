package com.example.api.controller;

import com.example.database.dto.JwtResponseDto;
import com.example.database.entity.User;
import com.example.database.service.JwtUserDetailsService;
import com.example.security.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

  @Autowired
  private JwtUserDetailsService userDetailsService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenManager tokenManager;

  @PostMapping("/login")
  public ResponseEntity<JwtResponseDto> createToken(@RequestBody final User request) {
    authenticate(request);
    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    final String jwtToken = tokenManager.generateJwtToken(userDetails);
    return ResponseEntity.ok(new JwtResponseDto(jwtToken));
  }

  private void authenticate(final User user) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(),
            user.getPassword())
    );
  }
}
