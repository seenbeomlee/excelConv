package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

//import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Approvement {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="customer_id")
  private Customer customerId;

  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="approvement_type")
  private Configure approvementType;

  private String userComment;

  @ManyToOne(targetEntity = ApprovementDetail.class, cascade = CascadeType.ALL)
  @JoinColumn(name="approvement_detail_id")
  private ApprovementDetail approvementDetailId;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name="target_customer_id")
  private Customer targetCustomerId;

  @ManyToOne(targetEntity = GroupInfo.class)
  @JoinColumn(name="target_group_id")
  private GroupInfo targetGroupId;

  @ManyToOne(targetEntity = VoucherState.class)
  @JoinColumn(name="target_voucher_state_id")
  private VoucherState targetVoucherStateId;

  @ManyToOne(targetEntity = DriveLog2.class)
  @JoinColumn(name="target_drive_log_id")
  private DriveLog2 targetDriveLog2Id;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;
}
