package com.autocrypt.fms.server2u.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import com.autocrypt.fms.server2u.common.core.FMSFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value="세부주행정보")
public class DriveLog2StatusLog {
  public DriveLog2StatusLog(DriveLog2 driveLog, Date doneTime) {
    if ( driveLog != null ) {
      Configure status = driveLog.getDriveStatus();
      id = FMSFactory.uuid();
      driveLogId = driveLog.getId();
      switch (status.getConfigValue()) {
        case "ds_wait":
          startTime = driveLog.getWaitTime();
          break;
        case "ds_drive":
          startTime = driveLog.getDriveTime();
          break;
        case "ds_service":
          startTime = driveLog.getServiceTime();
          break;
        case "ds_pause":
          startTime = driveLog.getPauseTime();
          break;
      }
      endTime = doneTime;
      duration = (int)(doneTime.getTime() - startTime.getTime()) / 1000;
      driveStatus = status;
    }
  }
  
  @ApiModelProperty(value="세부주행정보ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(value="주행정보ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String driveLogId;

  @ApiModelProperty(value="시작 일시")
  @Column(nullable = false, columnDefinition = "DATETIME")
  private Date startTime;

  @ApiModelProperty(value="종료 일시")
  @Column(nullable = false, columnDefinition = "DATETIME")
  private Date endTime;
  
  @ApiModelProperty(value="기간")
  @Column(nullable = false)
  private int duration;

  @ApiModelProperty(value="주행 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="drive_status")
  private Configure driveStatus;
}