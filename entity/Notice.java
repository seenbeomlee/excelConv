package com.autocrypt.fms.server2u.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @OneToOne(targetEntity = CenterInfo.class)
  @JoinColumn(name="center_id")
  @ApiModelProperty(value="센터ID")
  private CenterInfo centerId;

  @JsonIgnoreProperties({"centerId"})
  @OneToOne(targetEntity = Admin.class)
  @JoinColumn(name="creator_id")
  private Admin creatorId;

  @Length(max = 2)
  private String criticalFlag;

  @Length(max = 2)
  private String pushFlag;

  @Length(max = 1024)
  @Column(nullable = false)
  private String noticeTitle;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String noticeContents;

  @Column(nullable = false)
  private Date created;

  @Column(nullable = false)
  private Date updated;
}
