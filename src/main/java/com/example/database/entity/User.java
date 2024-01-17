package com.example.database.entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "user")
@Where(clause = "is_deleted=false")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String salt;
  private String role;
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
  }

  @Override
  public boolean isAccountNonExpired() {
    return !isDeleted;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !isDeleted;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !isDeleted;
  }

  @Override
  public boolean isEnabled() {
    return !isDeleted;
  }
}
