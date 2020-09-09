package com.autocrypt.fms.server2u.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceItem {
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

  private String regularInspection;

  private String engineOil;

  private String acFilter;

  private String frontTier;

  private String rearTier;

  private String battery;

  private String missionOil;

  private String coolant; //냉각수

  private String wiper;

  private String wheelAlignment;

  private String breakPad;

  private String breakOil;

  private String beam;

  public MaintenanceItem(@Length(max = 32) String id, @Length(max = 32) String centerId, Vehicle vehicleId) {
    this.id = id;
    this.centerId = centerId;
    this.vehicleId = vehicleId;
    this.regularInspection = null;
    this.engineOil = null;
    this.acFilter = null;
    this.frontTier = null;
    this.rearTier = null;
    this.battery = null;
    this.missionOil = null;
    this.coolant = null;
    this.wiper = null;
    this.wheelAlignment = null;
    this.breakPad = null;
    this.breakOil = null;
    this.beam = null;
  }
}
