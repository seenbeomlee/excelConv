package com.company.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Admin {

  @Id //PK 선언
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String loginId;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();

  @Column(nullable = false)
  private LocalDateTime updated = LocalDateTime.now();

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return name;
  }

  public boolean isAccountNonExpired() {
    return false;
  }

  public boolean isAccountNonLocked() {
    return false;
  }

  public boolean isCredentialsNonExpired() {
    return false;
  }

  public boolean isEnabled() {
    return false;
  }
}
