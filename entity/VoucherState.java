package com.autocrypt.fms.server2u.repository.entity;

import org.hibernate.validator.constraints.Length;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoucherState {
  @ApiModelProperty(value="바우처 state ID")
  @Id
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="센터 ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(value="바우처 ID")
  @ManyToOne(targetEntity = Voucher.class)
  @JoinColumn(name="voucher_id")
  private Voucher voucherId;

  @ApiModelProperty(value="바우처 state 발급 대상 ID")
  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @ApiModelProperty(value="바우처 state 지급 일자")
  private String issueDate;

  @ApiModelProperty(value="바우처 state 만료 일자")
  private String expirationDate;

  @ApiModelProperty(value="바우처 state 지급 개수")
  private String issueCnt;

  @ApiModelProperty(value="바우처 state 잔여 개수")
  private String remainCnt;

  @ApiModelProperty(value="바우처 state 사용 개수")
  private String useCnt;

  @ApiModelProperty(value="바우처 state 반환 개수")
  private String backCnt;

  @ApiModelProperty(value="바우처 state 만료 개수")
  private String expireCnt;

  // 바우처 발급자
  @ApiModelProperty(value="바우처 state 발급자 ID")
  @ManyToOne(targetEntity = Admin.class)
  @JoinColumn(name="admin_id")
  private Admin adminId;

  @ApiModelProperty(value="바우처 결재 id")
  @ManyToOne(targetEntity = Approvement.class)
  @JoinColumn(name="approvement_id")
  private Approvement approvementId;

  @ApiModelProperty(value="바우처 state 등록 일자")
  private Date created;

  @ApiModelProperty(value="바우처 state 갱신 일자")
  private Date updated;
}
