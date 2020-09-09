package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import com.autocrypt.fms.server2u.common.GlobalVariable;
import com.autocrypt.fms.server2u.common.core.FMSFactory;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VehicleModel {
  public VehicleModel(String centerId, String modelName, Configure seatCnt) {
    this.id = FMSFactory.uuid();
    this.centerId = centerId;
    this.modelName = modelName;
    this.seatCnt = seatCnt;
    this.state = GlobalVariable.ENABLE;
    this.created = new Date();
    this.updated = new Date();
  }

  @ApiModelProperty(value="차량모델ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="차량모델이름")
  @Length(max = 32)
  private String modelName;
  
  @ApiModelProperty(value="차종")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="seat_cnt")
  private Configure seatCnt;

  @ApiModelProperty(value="사용여부")
  @Length(max = 16)
  @Column(nullable = false)
  private String state;
  
  @ApiModelProperty(value="등록일시")
  @Column(nullable = false)
  private Date created;
  @ApiModelProperty(value="수정일시")
  @Column(nullable = false)
  private Date updated;

}