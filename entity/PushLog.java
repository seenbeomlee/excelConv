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
public class PushLog {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String reservationId;

  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driverId;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @Length(max = 1024)
  @Column(nullable = false)
  private String contents;

  @Length(max = 32)
  @Column(nullable = false)
  private String result;

  @Column(nullable = false)
  private Date created;
}
