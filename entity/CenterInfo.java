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
public class CenterInfo {

  //todo centerPoint, centerAddress 추가
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerName;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;

  private String centerAddress;

  private String centerAddressDetails;

  private String centerPoint;

  private String garageAddress;

  private String garageAddressDetails;

  private String garagePoint;

  private String number;
  
  @ApiModelProperty(value="사업자등록번호")
  @Length(max = 12)
  @Column(nullable = false)
  @ColumnDefault("''")
  private String registrationNumber;
  
  @ApiModelProperty(value="카드결제가맹점MID")
  @Length(max = 10)
  @ColumnDefault("''")
  private String cardMemberMid;
  @ApiModelProperty(value="카드결제appId")
  @Length(max = 4)
  @ColumnDefault("''")
  private String cardAppId;
  @ApiModelProperty(value="카드결제상품이름")
  @Length(max = 80)
  @ColumnDefault("''")
  private String cardGoodName;

  @ApiModelProperty(value="알림 문자 발송 여부")
  private String sms;
  
  @ApiModelProperty(value="예약 알림 문자 발송시간(분)")
  private int smsSendReservationAlertBefore;

  @ApiModelProperty(value="푸시 발송 여부")
  private String push;

  @ApiModelProperty(value="예약 알림 푸시 발송시간(분)")
  private int pushSendReservationAlertBefore;

  private String status;

  private String weekday;

  private String weekdayFirstResv;

  private String weekdayLastResv;

  private String weekdayFirstDrive;

  private String weekdayLastDrive;

  private String weekend;

  private String weekendFirstResv;

  private String weekendLastResv;

  private String weekendFirstDrive;

  private String weekendLastDrive;
}

