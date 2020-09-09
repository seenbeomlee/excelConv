package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import com.autocrypt.fms.server2u.common.core.FMSFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="주행결제로그")
public class DriveLog2PayLog {
  public DriveLog2PayLog(DriveLog2 driveLog, Admin admin, Configure payLogType, DriveLog2CardLog cardLog) {
    id = FMSFactory.uuid();
    driveLogId = driveLog.getId();
    this.payLogType = payLogType;
    
    payMethod = driveLog.getPayMethod();
    payStatus = driveLog.getPayStatus();
    extraPayMethod = driveLog.getExtraPayMethod();
    
    totalFee = driveLog.getTotalFee();
    waitFee = driveLog.getWaitFee();
    driveFee = driveLog.getDriveFee();
    serviceFee = driveLog.getServiceFee();
    extraFee = driveLog.getExtraFee();
    paidFee = driveLog.getPaidFee();
    unpaidFee = driveLog.getUnpaidFee();
    extraPaidFee = driveLog.getExtraPaidFee();
    
    this.cardLog = cardLog;
    logBy = admin;
    logTime = new Date();
  }
  
  @ApiModelProperty(value="주행결제로그ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="주행정보ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String driveLogId;
  
  @ApiModelProperty(value="주행결제로그종류")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="pay_log_type")
  private Configure payLogType;
  
  @ApiModelProperty(value="결제 방법")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="pay_method")
  private Configure payMethod;
  
  @ApiModelProperty(value="결제 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="pay_status")
  private Configure payStatus;
  
  @ApiModelProperty(value="추가 결제 방법")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="extra_pay_method")
  private Configure extraPayMethod;

  @ApiModelProperty(value="전체 요금(원)")
  @Column(nullable = true)
  private int totalFee;
  @ApiModelProperty(value="대기 요금(원)")
  @Column(nullable = true)
  private int waitFee;
  @ApiModelProperty(value="주행 요금(원)")
  @Column(nullable = true)
  private int driveFee;
  @ApiModelProperty(value="부가서비스 요금(원)")
  @Column(nullable = true)
  private int serviceFee;
  @ApiModelProperty(value="추가 요금(원)")
  @Column(nullable = true)
  private int extraFee;
  @ApiModelProperty(value="결제 요금(원)")
  @Column(nullable = true)
  private int paidFee;
  @ApiModelProperty(value="미결제 요금(원)")
  @Column(nullable = true)
  private int unpaidFee;
  @ApiModelProperty(value="추가 결제 요금(원)")
  @Column(nullable = true)
  private int extraPaidFee;

  @ApiModelProperty(value="카드 결제 로그")
  @ManyToOne(targetEntity = DriveLog2CardLog.class)
  @JoinColumn(name="card_log_id")
  private DriveLog2CardLog cardLog;
  
  @ApiModelProperty(value="수정한 계정")
  @ManyToOne(targetEntity = Admin.class)
  @JoinColumn(name="log_by")
  private Admin logBy;
  
  @ApiModelProperty(value="로그 시간 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date logTime;
}