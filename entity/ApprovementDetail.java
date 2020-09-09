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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ApprovementDetail {
  @Id
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  // 결재 승인자
  @ManyToOne(targetEntity = Admin.class)
  @JoinColumn(name="admin_id")
  private Admin adminId;

  // 진행상황
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="state")
  private Configure state;

  private String adminComment;

  private String approvementId;

  private Date created;

  private Date updated;
}
