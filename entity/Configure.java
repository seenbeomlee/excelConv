package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Configure {
  @Length(max = 32)
  @Column(nullable = false)
  private String configKey;

  @Length(max = 128)
  @Column(nullable = false)
  private String configName;

  @Id //PK 선언
  @Length(max = 16)
  @Column(nullable = false)
  private String configValue;
}
