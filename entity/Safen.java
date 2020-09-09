package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Safen {
  @Id //PK 선언
  @Length(max = 20)
  @Column(nullable = false)
  private String phone;

  @Length(max = 20)
  @Column(nullable = false)
  private String limitedDate;
  
  @Length(max = 20)
  @Column(nullable = false)
  private String safenNo;
 
  @Transient
  public boolean isExpired() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String nowStr = dateFormat.format(new Date());
    
    return nowStr.compareTo(limitedDate) > 0;
  }
  
  @Transient
  public boolean needRenewal(String newLimitedTime) {
    return newLimitedTime.compareTo(limitedDate) > 0;
  }
}