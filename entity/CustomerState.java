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
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerState {

  @Id
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Column
  private String centerId;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @Column
  private String active;

  @ManyToOne(targetEntity=Admin.class)
  @JoinColumn(name="admin_id")
  private Admin adminId;

  @Column
  private Date created;
}
