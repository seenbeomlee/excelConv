package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SafenLog {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 20)
  @Column(nullable = false)
  private String phone;

  @Length(max = 20)
  @Column(nullable = false)
  private String groupCode;

  @Length(max = 20)
  @Column(nullable = false)
  private String sendDate;

  @Length(max = 1)
  @Column(nullable = false)
  private String newFlag;

  @Length(max = 20)
  @Column(nullable = false)
  private String limitedDate;
  
  @Length(max = 10)
  @Column(nullable = true)
  private String result;
  
  @Length(max = 20)
  @Column(nullable = true)
  private String safenNo;
  
  @Length(max = 1024)
  @Column(nullable = true)
  private String resultBody;
  
  @Column(nullable = false)
  private Date created;
  
  @Column(nullable = false)
  private Date updated;
  
  @Transient
  public Safen getSafen() {
    return new Safen(phone, limitedDate, safenNo);
  }
}
