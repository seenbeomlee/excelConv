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
public class PushDevice {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String deviceKey;

  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="device_type")
  private Configure deviceType;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;
}
