package com.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity // Table
public class ApiAccount {
  @Id //PK 선언
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String organizationName;

//  @Length(max = 10)
//  @Column(nullable = false)
//  private String actor;

  @Column(nullable = false)
  private String hKey;

  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();

}
