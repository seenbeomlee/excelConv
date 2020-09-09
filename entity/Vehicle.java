package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vehicle {

  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ManyToOne(targetEntity = Driver.class)
  private Driver mainDriver;

  @ManyToOne(targetEntity = Driver.class)
  private Driver subDriver1;

  @ManyToOne(targetEntity = Driver.class)
  private Driver subDriver2;

  @Length(max = 16)
  @Column(nullable = false)
  private String plateNo;

  @ManyToOne(targetEntity = Configure.class)
  private Configure seatCnt;

  @Length(max = 32)
  private String modelName;
  
  @ManyToOne(targetEntity = VehicleModel.class)
  @JoinColumn(name="vehicle_model_id")
  private VehicleModel vehicleModel;

  @Length(max = 8)
  @Column(nullable = false)
  private String hwUsage;

  @Length(max = 16)
  @Column(nullable = false)
  private String inspectionDate;

  @Length(max = 16)
  @Column(nullable = false)
  private String registrationDate;

  @Length(max = 16)
  @Column(nullable = false)
  private String expirationDate;

  @Length(max = 16)
  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;

  
  /**
   * 이전에 사용하던 modelName은 더 이상 사용하지 않고 vehicleModel.ModelName을 사용하여야 한다.
   * 기사앱API에서 정상적인 데이터를 제공하기 위해
   * getModelName() 함수를 새로 만든다.
   * @return
   */
  public String getModelName() {
    return vehicleModel != null ? vehicleModel.getModelName() : "";
  }
  
  /**
   * modelName에서 vehicleModel.ModelName로 데이터 정리를 위해
   * 이전에 입력된 modelName을 확인할 필요가 있다.
   * ApplicationInitial.vehicleModelInit() 에서만 사용한다.
   * @return
   */
  @Transient
  public String getModelName2() {
    return modelName;
  }
}
