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
public class VoucherLog {
  @ApiModelProperty(value="바우처 log ID")
  @Id
  @Length(max=32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터 ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="바우처 log 사용 회원 ID")
  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @ApiModelProperty(value="바우처 state ID")
  @ManyToOne(targetEntity = VoucherState.class)
  @JoinColumn(name="voucher_state_id")
  private VoucherState voucherStateId;

  @ApiModelProperty(value="바우처 log 결재 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="state")
  private Configure state;

  @ApiModelProperty(value="바우처 log 결재 상세 ID")
  @ManyToOne(targetEntity = ApprovementDetail.class)
  @JoinColumn(name="approvement_detail_id")
  private ApprovementDetail approvementDetailId;

  @ApiModelProperty(value="바우처 log 예약 ID")
  private String reservationId;

  @ApiModelProperty(value="바우처 log 등록 일자")
  private Date created;

  @ApiModelProperty(value="바우처 log 갱신 일자")
  private Date updated;
}
