package com.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity // Table

public class ApiLog {
  @Id //PK 선언
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();

  @Column(nullable = false)
  private String url;

  @Column(nullable = false)
  private String method;

  @Column(nullable = false)
  private String level;

  @Column(nullable = false)
  private String sourceIp;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String request;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String response;

}
