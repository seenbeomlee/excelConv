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
public class VehicleLocation {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driverId;

  @ManyToOne(targetEntity = Vehicle.class)
  @JoinColumn(name="vehicle_id")
  private Vehicle vehicleId;

  @Length(max = 64)
  private String latitude;

  @Length(max = 64)
  private String longitude;

  @Length(max = 64)
  private String driveStatus;

  @Length(max = 32)
  private String reservationId;

  @Column(nullable = false)
  private String created;
}
