package com.autocrypt.fms.server2u.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Refueling {
  @Id
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @OneToOne(targetEntity = Vehicle.class)
  @JoinColumn(name="vehicle_id")
  private Vehicle vehicleId;

  private String date;

  private String time;

  private String liter;

  private String gasStation;

  private String price;

  private Date created;

  private Date updated;
}
