package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
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

  @Length(max = 8)
  private String gender;

  @ApiModelProperty(value="회원 주소")
  @Length(max = 128)
  private String address;
  @ApiModelProperty(value="회원 상세주소")
  @Length(max = 128)
  private String addressDetails;
  @ApiModelProperty(value="회원 주소 위도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  @ColumnDefault("0")
  private float addressLat;
  @ApiModelProperty(value="회원 주소 경도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  @ColumnDefault("0")
  private float addressLng;
  
  @Length(max = 32)
  @Column(nullable = false)
  private String mobileNo;
  
  @Length(max = 100)
  @ColumnDefault("''")
  private String email;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyNo;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyRelation;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyNo2nd;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyRelation2nd;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyNo3rd;

  @Length(max = 32)
  @Column(nullable = true)
  private String familyRelation3rd;

  @Length(max = 32)
  private String birth;

  @Length(max = 32)
  @Column(nullable = false)
  private String state;
  
  @ApiModelProperty(value="활성/비활성")
  @Length(max = 32)
  @Column(nullable = false)
  private String active;

  @ApiModelProperty(value="서비스 이용 횟수")
  @Column(nullable = false)
  private Integer usedCount;

  @ApiModelProperty(value="취소 횟수")
  @Column(nullable = false)
  private Integer canceledCount;
  
  @ApiModelProperty(value="사용자 무단 취소 횟수")
  @Column(nullable = false)
  private Integer userCanceledCount;

  @ApiModelProperty(value="비밀번호")
  @Length(max = 64)
  @Column
  private String password;

  @ApiModelProperty(value="로그인 상태 확인")
  @Length(max = 32)
  @Column
  private String accessToken;

  @ApiModelProperty(value="회원 가입 방법")
  @Column
  private String registerType;

  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="customer_type")
  private Configure customerType;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="joiner_id")
  private Customer joinerId;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;
}
