package com.autocrypt.fms.server2u.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver {

  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @Length(max = 32)
  @Column(nullable = false)
  private String name;

  @Length(max = 128)
  private String address;

  @Length(max = 32)
  private String birth;

  @Length(max = 8)
  private String gender;

  @Length(max = 32)
  @Column(nullable = false)
  private String mobileNo;

  @Length(max = 32)
  @Column(nullable = false)
  private String licenseNo;

  @Length(max = 8)
  @Column(nullable = false)
  private String onwork;

  @Length(max = 16)
  @Column(nullable = false)
  private String state;

  @Length(max = 64)
  @Column(nullable = true)
  private String photo;

  @Length(max = 64)
  @Column(nullable = true)
  private String employeeNo;

  @OneToOne(targetEntity = PushDevice.class)
  @JoinColumn(name="device_id")
  private PushDevice deviceId;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;
}
