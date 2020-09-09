package com.autocrypt.fms.server2u.repository.entity;

import com.autocrypt.fms.server2u.common.core.FMSFactory;
import com.autocrypt.fms.server2u.resource.lostItem.model.LostItemReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LostItem {

  @ApiModelProperty(value="분실물 ID")
  @Id //PK 선언
  @Length(max = 32)
  @Column(nullable = false)
  private String id;

  @ApiModelProperty(required=true, value="센터 ID")
  @Length(max = 32)
  @Column(nullable = false)
  private String centerId;

  @ApiModelProperty(required=true, value="분실물 타입")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="type")
  private Configure type;

  @ApiModelProperty(required=true, value="습득차량 [format: '차량번호|차량종류']")
  @Length(max = 30)
  @Column(nullable = false)
  private String vehicle;

  @ApiModelProperty(required=true, value="기사 정보")
  @ManyToOne(targetEntity = Driver.class)
  @JoinColumn(name="driver_id")
  private Driver driver;

  @ApiModelProperty(required=true, value="습득일 [format: 'YYYY-MM-DD HH:mm']")
  @Length(max = 19)
  @Column(nullable = false)
  private String getDate;

  @ApiModelProperty(value="분실물 요약")
  @Length(max = 30)
  private String summary;

  @ApiModelProperty(value="분실물 상세")
  @Length(max = 500)
  private String detail;

  @ApiModelProperty(required=true, value="분실물 상태")
  @ManyToOne(targetEntity = Configure.class)
  @JoinColumn(name="state")
  private Configure state;

  @ApiModelProperty(required=true, value="분실물 사진 URL")
  @Length(max = 64)
  private String photoUrl;

  @ApiModelProperty(required=true, value="분실물 등록 일자")
  @Column(nullable = false)
  private Date created;

  @ApiModelProperty(required=true, value="분실물 수정 일자")
  @Column(nullable = false)
  private Date updated;

  public LostItem(LostItemReq lostItemReq) {
    this.id = FMSFactory.uuid();
    this.centerId = FMSFactory.getCenterId();
    if(this.centerId == null)
      this.centerId = lostItemReq.getCenterId();

    this.getDate = lostItemReq.getGetDate();
    this.vehicle = lostItemReq.getVehicle();
    this.detail = lostItemReq.getDetail();
    this.summary = lostItemReq.getSummary();

    this.created = new Date();
    this.updated = new Date();
  }
}
