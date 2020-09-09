package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.autocrypt.fms.server2u.common.exception.BadRequestException;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation implements Comparable<Reservation> {
  @ApiModelProperty(value="예약 아이디")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터 아이디")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="예약 아이디 (차량 대수에 의한 그룹 아이디)")
  @Length(max = 32)
  @Column(nullable = false)
  private String reservationId;

  @ApiModelProperty(value="기사 정보")
  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driverId;

  @ApiModelProperty(value="기사 연락처(안심번호)")
  @Length(max = 32)
  @Column
  private String driverSafenNo;

  @ApiModelProperty(value="차량 정보")
  @OneToOne(targetEntity = Vehicle.class)
  @JoinColumn(name="vehicle_id")
  private Vehicle vehicleId;

  @ApiModelProperty(value="고객 아이디")
  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @ApiModelProperty(value="고객명")
  @Length(max = 32)
  @Column
  private String customerName;

  @ApiModelProperty(value="고객 연락처")
  @Length(max = 32)
  @Column
  private String customerNo;

  @ApiModelProperty(value="고객 연락처(안심번호)")
  @Length(max = 32)
  @Column
  private String customerSafenNo;

  @ApiModelProperty(value="보호자 연락처")
  @Length(max = 32)
  @Column
  private String familyNo;

  @ApiModelProperty(value="보호자 연락처(안심번호)")
  @Length(max = 32)
  @Column
  private String familySafenNo;

  @ApiModelProperty(value="보호자 관계")
  @Length(max = 32)
  @Column
  private String familyRelation;

  @ApiModelProperty(value="출발지 주소")
  @Length(max = 320)
  @Column(nullable = false)
  private String departure;

  @ApiModelProperty(value="출발지 좌표")
  @Length(max = 60)
  @Column(nullable = false)
  private String departurePoint;

  @ApiModelProperty(value="도착지 주소")
  @Length(max = 320)
  @Column(nullable = false)
  private String destination;

  @ApiModelProperty(value="도착지 좌표")
  @Length(max = 60)
  @Column(nullable = false)
  private String destinationPoint;

  @ApiModelProperty(value="경유지1 주소")
  @Length(max = 320)
  @Column
  private String waypoint1;

  @ApiModelProperty(value="경유지1 좌표")
  @Length(max = 60)
  @Column
  private String waypoint1Point;

  @ApiModelProperty(value="경유지2")
  @Length(max = 320)
  @Column
  private String waypoint2;

  @ApiModelProperty(value="경유지2 좌표")
  @Length(max = 60)
  @Column
  private String waypoint2Point;

  @ApiModelProperty(value="경유지3 주소")
  @Length(max = 320)
  @Column
  private String waypoint3;

  @ApiModelProperty(value="경유지3 좌표")
  @Length(max = 60)
  @Column
  private String waypoint3Point;

  @ApiModelProperty(value="탑승 인원")
  @Column(nullable = false)
  private Integer customerCnt;

  @ApiModelProperty(value="차량 대수")
  @Column(nullable = false)
  private Integer vehicleCnt;

  @ApiModelProperty(value="차종")
  @Length(max = 256)
  @Column(nullable = false)
  private String vehicleType;

  @ApiModelProperty(value="용도")
  @Length(max = 256)
  @Column(nullable = false)
  private String customerUsage;

  @ApiModelProperty(value="수하물")
  @Length(max = 32)
  @Column(nullable = false)
  private String luggage;

  @ApiModelProperty(value="서비스 시간")
  @Column(nullable = false)
  private Integer serviceTime;

  @ApiModelProperty(value="서비스 시간에 대한 고객 요청 사항")
  @Length(max = 32)
  @Column(nullable = true)
  private String customerComment;

  @ApiModelProperty(value="출발 시간")
  @Length(max = 32)
  @Column(nullable = false)
  private String startTime;

  @ApiModelProperty(value="도착 예상 시간")
  @Length(max = 32)
  @Column(nullable = false)
  private String arriveTime;

  @ApiModelProperty(value="예상 소요 시간")
  @Column(nullable = false)
  private Integer driveTime;

  @ApiModelProperty(value="서비스 종료 시간")
  @Length(max = 32)
  @Column(nullable = false)
  private String endTime;

  @ApiModelProperty(value="예상 요금")
  @Column(nullable = false)
  private Integer fee;

  @ApiModelProperty(value="예약 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="resrv_state")
  private Configure resrvState;

  @ApiModelProperty(value="시외 요금")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="suburban")
  private Configure suburban;

  @ApiModelProperty(value="배차 종류")
  @Length(max = 2)
  private String assignType;

  @ApiModelProperty(value="생성 일자")
  @Column(nullable = false)
  private Date created;

  @ApiModelProperty(value="수정 일자")
  @Column(nullable = false)
  private Date updated;

  @ApiModelProperty(value="출발지 추가정보")
  @Column
  private String departureComment;

  @ApiModelProperty(value="도착지 추가정보")
  @Column
  private String destinationComment;

  @ApiModelProperty(value="경유지1 추가정보")
  @Column
  private String waypoint1Comment;

  @ApiModelProperty(value="경유지2 추가정보")
  @Column
  private String waypoint2Comment;

  @ApiModelProperty(value="경유지3 추가정보")
  @Column
  private String waypoint3Comment;

  @ApiModelProperty(value="예약안내문자발송여부")
  private int sendSmsToCustomer;

  @ApiModelProperty(value="예약안내푸시발송여부")
  @Column(nullable = true)
  private int sendPushToCustomer;

  @ApiModelProperty(value="미출발확인푸시발송여부")
  private int sendPushToDriver;

  @ManyToOne(targetEntity = VoucherState.class)
  @JoinColumn(name="voucher_state_id")
  private VoucherState voucherStateId;

  /**
   * 예약 접수시 예약값의 유효성을 확인한다.
   * @throws BadRequestException
   */
  @Transient
  public void validateRequest() throws BadRequestException {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date now = new Date();
      Date start = dateFormat.parse(startTime);
      if ( now.getTime() > start.getTime() ) {
        throw new BadRequestException("과거 시간은 예약이 불가능합니다. 시간을 다시 설정해주세요.");
      }
    } catch (ParseException e) {
      throw new BadRequestException("출발시간 입력값이 잘못되었습니다.");
    }
  }

  /**
   * 일반 예약 정보가 아니라 휴식관련 정보인지 확인한다.
   * 휴식(customerName == departure == destination)
   * @return
   */
  @Transient
  public boolean isRest() {
    if ( StringUtils.isEmpty(customerName) ) {
      return StringUtils.isEmpty(departure) && StringUtils.isEmpty(destination);
    } else {
      return customerName.equals(departure) && customerName.equals(destination);
    }
  }

  @Override
  public int compareTo(@NotNull Reservation o) {
    return o.getStartTime().compareTo(this.startTime);
  }
}
