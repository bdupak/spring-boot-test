package com.example.security.jwt;

import com.example.database.service.JwtUserDetailsService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String HEADER_NAME = "Authorization";
  @Autowired
  private JwtUserDetailsService userDetailsService;
  @Autowired
  private TokenManager tokenManager;

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
                                  final HttpServletResponse response,
                                  final FilterChain filterChain)
      throws ServletException, IOException {
    final String tokenHeader = request.getHeader(HEADER_NAME);
    String username = null;
    String token = null;
    if (Objects.nonNull(tokenHeader) && tokenHeader.startsWith(BEARER_PREFIX)) {
      token = tokenHeader.substring(BEARER_PREFIX.length());
      try {
        username = tokenManager.extractUserName(token);
      } catch (IllegalArgumentException e) {
        LOGGER.error("Unable to get JWT Token");
      }
    } else {
      LOGGER.error("Bearer String not found in token");
    }

    constructAuthentication(request, username, token);
    filterChain.doFilter(request, response);
  }

  private void constructAuthentication(final HttpServletRequest request,
                                       final String username,
                                       final String token) {
    if (Objects.nonNull(username) &&
        SecurityContextHolder.getContext().getAuthentication() == null) {
      final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (tokenManager.validateJwtToken(token, userDetails)) {
        SecurityContextHolder.getContext()
            .setAuthentication(createAuthenticationToken(request, userDetails));
      }
    }
  }

  private UsernamePasswordAuthenticationToken createAuthenticationToken(
      final HttpServletRequest request,
      final UserDetails userDetails) {
    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(
            userDetails, null,
            userDetails.getAuthorities());
    authenticationToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request));
    return authenticationToken;
  }
}
