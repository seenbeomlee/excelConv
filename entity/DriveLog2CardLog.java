package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import com.autocrypt.fms.server2u.common.core.FMSFactory;
import com.autocrypt.fms.server2u.manage.mobile.driver.model.DriveCardLogReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="카드결제정보")
public class DriveLog2CardLog {
  public DriveLog2CardLog(CenterInfo centerInfo, Reservation reservation, DriveLog2 driveLog, Configure cardLogType) {
    id = FMSFactory.uuid();
    driveLogId = driveLog.getId();
    this.cardLogType = cardLogType;
    
    appId = centerInfo.getCardAppId();
    mid = centerInfo.getCardMemberMid();
    goodName = centerInfo.getCardGoodName();
    orderKey = FMSFactory.uuid();
    
    noComp = centerInfo.getRegistrationNumber().replaceAll("[^0-9.]", "");
    custName = reservation.getCustomerName();
    tel = reservation.getCustomerNo();
    if ( reservation.getCustomerId() != null ) {
      email = reservation.getCustomerId().getEmail();
    } else {
      email = "";
    }
    
    orderTime = new Date();
  }
  
  public DriveLog2CardLog(DriveLog2CardLog cardLog) {
    id = FMSFactory.uuid();
    driveLogId = cardLog.getDriveLogId();
    
    appId = cardLog.getAppId();
    mid = cardLog.getMid();
    goodName = cardLog.getGoodName();
    orderKey = cardLog.getOrderKey();
    
    noComp = cardLog.getNoComp();
    custName = cardLog.getCustName();
    tel = cardLog.getTel();
    email = cardLog.getEmail();
    
    orderTime = new Date();
  }
  
  @ApiModelProperty(value="카드결제정보ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="주행정보ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String driveLogId;
  
  @ApiModelProperty(value="카드결제로그종류")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="card_log_type")
  private Configure cardLogType;

  @ApiModelProperty(value="AppID")
  @Length(max = 4)
  private String appId;
  @ApiModelProperty(value="가맹점ID")
  @Length(max = 10)
  private String mid;
  @ApiModelProperty(value="상품명")
  @Length(max = 32)
  private String goodName;
  @ApiModelProperty(value="주문번호")
  @Length(max = 64)
  private String orderKey;
  
  @ApiModelProperty(value="사업자번호")
  @Length(max = 10)
  private String noComp;
  @ApiModelProperty(value="구매자명")
  @Length(max = 10)
  private String custName;
  @ApiModelProperty(value="전화번호")
  @Length(max = 20)
  private String tel;
  @ApiModelProperty(value="이메일")
  @Length(max = 40)
  private String email;
  
  @ApiModelProperty(value="결과 코드")
  private String result;
  @ApiModelProperty(value="결과 메시지")
  private String msg;

  @ApiModelProperty(value="신용카드 승인번호")
  private String noAppl;
  @ApiModelProperty(value="승인날짜")
  private String appDate;
  @ApiModelProperty(value="승인시간")
  private String appTime;
  @ApiModelProperty(value="신용카드 취소승인번호")
  private String noCancel;
  @ApiModelProperty(value="취소날짜")
  private String cancelDate;
  @ApiModelProperty(value="취소시간")
  private String cancelTime;

  @ApiModelProperty(value="거래번호")
  @Length(max = 40)
  private String tid;
  @ApiModelProperty(value="거래상태")
  @Length(max = 40)
  private String clTrans;

  @ApiModelProperty(value="카드코드")
  private String cardCode;
  @ApiModelProperty(value="카드사명")
  private String cardNm;  
  @ApiModelProperty(value="결과 금액")
  @Length(max = 40)
  private String prGoods;

  @ApiModelProperty(value="결제 요청 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date orderTime;
  @ApiModelProperty(value="결제 결과 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date resultTime;
  
  @Transient
  public void setResult(DriveCardLogReq request) {
    result = request.getResult();
    msg = request.getMsg();
    
    noAppl = request.getNoAppl();
    appDate = request.getAppDate();
    appTime = request.getAppTime();
    noCancel = request.getNoCancel();
    cancelDate = request.getCancelDate();
    cancelTime = request.getCancelTime();
    
    tid = request.getTid();
    clTrans = request.getClTrans();

    cardCode = request.getCardCode();
    cardNm = request.getCardNm();
    prGoods = request.getPrGoods();
    
    resultTime = new Date();
  }
}