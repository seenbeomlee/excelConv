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
public class SMSLog {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String reservationId;

  @Length(max = 32)
  @Column(nullable = false)
  private String mobileNo;

  @Length(max = 1000)
  @Column(nullable = false)
  private String contents;

  @Length(max = 32)
  @Column(nullable = false)
  private String result;

  @Column(nullable = false)
  private Date created;
}
