package com.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity // Table

public class AdminLog {
  @Id //PK 선언
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String loginId;

  @Column(nullable = false)
  private String sourceIp;

  @Column(nullable = false)
  private String level;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String details;

  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();
}
