package com.autocrypt.fms.server2u.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {
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

  private String type;

  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driver;

  @ManyToOne(targetEntity = Admin.class)
  @JoinColumn(name="admin_id")
  private Admin admin;

  private String location;

  private String date;

  private String time;

  private String price;

  private String changeList;

  private String changeListObject;

  private String changeEtc;

  private String maintenanceList;

  private String maintenanceListObject;

  private String maintenanceEtc;

  private Date created;

  private Date updated;
}
