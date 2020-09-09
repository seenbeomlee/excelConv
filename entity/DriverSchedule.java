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
public class DriverSchedule {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @Length(max = 16)
  @Column(nullable = false)
  private String scheduleDate;

  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driverId;

  @OneToOne(targetEntity = Vehicle.class)
  @JoinColumn(name="vehicle_id")
  private Vehicle vehicleId;

  @Length(max = 16)
  @Column(nullable = false)
  private String onWorkTime;

  @Length(max = 16)
  @Column(nullable = false)
  private String endWorkTime;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;

}
