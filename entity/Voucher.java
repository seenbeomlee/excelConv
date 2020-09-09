package com.autocrypt.fms.server2u.repository.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voucher {
  @ApiModelProperty(value="바우처 ID")
  @Id
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터 ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="바우처 이름")
  private String name;

  @ApiModelProperty(value="바우처 타입")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="voucherType")
  private Configure voucherType;

  @ApiModelProperty(value="바우처 유형 임산부")
  private Boolean mother;

  @ApiModelProperty(value="바우처 유형 영유아")
  private Boolean infants;

  @ApiModelProperty(value="바우처 유형 일반")
  private Boolean general;

  @ApiModelProperty(value="바우처 유형 ON, OFF 타입")
  private String regitType;

  @ApiModelProperty(value="바우처 발급 기관")
  private String organization;

  @ApiModelProperty(value="바우처 상태 Disable, Enable")
  private String state;

  @ApiModelProperty(value="바우처 등록 일자")
  private Date created;

  @ApiModelProperty(value="바우처 갱신 일자")
  private Date updated;
}
