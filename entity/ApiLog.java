package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiLog {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private Date created;

  @Length(max = 500)
  @Column(nullable = false)
  private String url;

  @Length(max = 16)
  @Column(nullable = false)
  private String method;

  @Length(max = 128)
  @Column(nullable = false)
  private String remoteAddr;

  @Column(columnDefinition = "TEXT")
  private String appInfo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String request;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String response;

}
