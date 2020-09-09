package com.autocrypt.fms.server2u.repository.entity;

import com.autocrypt.fms.server2u.common.core.FMSFactory;
import com.autocrypt.fms.server2u.manage.reservation.model.UpdateDriveLogFeeReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="주행정보")
public class DriveLog2 {
  @ApiModelProperty(value="주행정보ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="예약ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String reservationId;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @ApiModelProperty(value="예약자성함")
  @Length(max = 32)
  @Column(nullable = true)
  private String customerName;
  
  @ApiModelProperty(hidden = true)
  @Transient
  private Reservation reservation;

  @ApiModelProperty(value="기사 정보")
  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driverId;

  @ApiModelProperty(value="차량 정보")
  @ManyToOne(targetEntity = Vehicle.class)
  @JoinColumn(name="vehicle_id")
  private Vehicle vehicleId;
  
  @ApiModelProperty(value="주행 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="drive_status")
  private Configure driveStatus;
  
  @ApiModelProperty(value="탑승 인원")
  @Column(nullable = true)
  private int riderCount;
  
  @ApiModelProperty(value="시외 주행")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="suburban")
  private Configure suburban;

  @ApiModelProperty(value="출발지주소")
  @Column(nullable = true)
  private String addr0;
  @ApiModelProperty(value="출발지위도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  private float lat0;
  @ApiModelProperty(value="출발지경도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  private float lng0;
  
  @ApiModelProperty(value="도착지주소")
  @Column(nullable = true)
  private String addr1;
  @ApiModelProperty(value="도착지위도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  private float lat1;
  @ApiModelProperty(value="도착지경도")
  @Column(nullable = true, columnDefinition = "FLOAT(12,8)")
  private float lng1;

  @ApiModelProperty(value="시작 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date startTime;
  @ApiModelProperty(value="대기 시작 일시(최근)")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date waitTime;
  @ApiModelProperty(value="주행 시작 일시(최근)")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date driveTime;
  @ApiModelProperty(value="부가서비스 시작 일시(최근)")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date serviceTime;
  @ApiModelProperty(value="중단 시작 일시(최근)")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date pauseTime;
  @ApiModelProperty(value="종료 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date endTime;
  @ApiModelProperty(value="결제완료 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date payTime;
  @ApiModelProperty(value="추가결제 일시")
  @Column(nullable = true, columnDefinition = "DATETIME")
  private Date extraPayTime;

  @ApiModelProperty(value="전체 시간(초)")
  @Column(nullable = true)
  private int totalDuration;
  @ApiModelProperty(value="대기 시간(초)")
  @Column(nullable = true)
  private int waitDuration;
  @ApiModelProperty(value="주행 시간(초)")
  @Column(nullable = true)
  private int driveDuration;
  @ApiModelProperty(value="부가서비스 시간(초)")
  @Column(nullable = true)
  private int serviceDuration;
  @ApiModelProperty(value="중단 시간(초)")
  @Column(nullable = true)
  private int pauseDuration;

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
  
  @ApiModelProperty(value="추가 결제자 이름")
  @Length(max = 32)
  @Column(nullable = true)
  private String extraPayCustomerName;

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
  
  @ApiModelProperty(value="주행 거리")
  @Column(nullable = true)
  private int totalDistance;

  @ApiModelProperty(value="이동경로")
  @Column(nullable = true, columnDefinition = "LONGTEXT")
  private String pathLog;
  @ApiModelProperty(value="운행 상태 로그")
  @Column(nullable = true, columnDefinition = "LONGTEXT")
  private String statusLog;
  
  @ApiModelProperty(value="생성 일시")
  @Column(nullable = false, columnDefinition = "DATETIME")
  private Date created;
  @ApiModelProperty(value="수정 일시")
  @Column(nullable = false, columnDefinition = "DATETIME")
  private Date updated;

  @Transient
  public boolean isDone() {
    return "ds_done".equals(driveStatus.getConfigValue());
  }
  
  @Transient
  public void computeWait(Date nowDate) {
    waitDuration += (nowDate.getTime() - waitTime.getTime())/1000;
    if(centerId.equals("6X-KUQC8QlmiT4m8TbJcKA")) {
      waitFee = 0;
    } else {
      waitFee = Integer.parseInt(FMSFactory.computeWaitFee(waitDuration, !suburban.getConfigValue().equalsIgnoreCase("0suburban")));
    }
    computeTotalFee();
  }
  
  @Transient
  public void computeDrive(Date nowDate) {
    driveDuration += (nowDate.getTime() - driveTime.getTime())/1000;
    if(centerId.equals("6X-KUQC8QlmiT4m8TbJcKA")) {
      driveFee = 0;
    } else {
      if ( suburban.getConfigValue().equalsIgnoreCase("0suburban") ) {
        driveFee = Integer.parseInt(FMSFactory.computeDriveFee(driveDuration));
      } else {
        String suburbanStr = suburban.getConfigName();
        String suburbanFee = suburbanStr.substring(suburbanStr.lastIndexOf("|") + 1, suburbanStr.length());
        driveFee = Integer.parseInt(suburbanFee.replaceAll("[^0-9.]", ""));
      }
    }
    computeTotalFee();
  }
  
  @Transient
  public void computeService(Date nowDate) {
    serviceDuration += (nowDate.getTime() - serviceTime.getTime())/1000;
    if(centerId.equals("6X-KUQC8QlmiT4m8TbJcKA")) {
      serviceFee = 0;
    } else {
      serviceFee = Integer.parseInt(FMSFactory.computeWaitFee(serviceDuration, !suburban.getConfigValue().equalsIgnoreCase("0suburban")));
    }
    computeTotalFee();
  }
  
  @Transient
  public void computePause(Date nowDate) {
    pauseDuration += (nowDate.getTime() - pauseTime.getTime())/1000;
  }
  
  @Transient
  public void computeTotalFee() {
    totalFee = waitFee+driveFee+serviceFee+extraFee;
    unpaidFee = totalFee - paidFee - extraPaidFee;
  }
  
  @Transient
  public void setFeeRequest(UpdateDriveLogFeeReq request) {
    waitFee = request.getWaitFee();
    driveFee = request.getDriveFee();
    serviceFee = request.getServiceFee();
    extraFee = request.getExtraFee();
  }
}
